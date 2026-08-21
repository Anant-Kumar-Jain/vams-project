package com.vams.controller;

import com.vams.model.*;
import com.vams.repository.AccessRequestRepository;
import com.vams.repository.ScanLogRepository;
import com.vams.repository.SystemLogRepository;
import com.vams.repository.UserRepository;
import com.vams.repository.GateRepository;
import com.vams.service.ManagerService;
import com.vams.service.QRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired private UserRepository userRepository;
    @Autowired private AccessRequestRepository requestRepository;
    @Autowired private ScanLogRepository scanLogRepository;
    @Autowired private SystemLogRepository systemLogRepository;
    @Autowired private GateRepository gateRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private ManagerService managerService;
    @Autowired private QRService qrService;

    @GetMapping("/verify-email-notice")
    public String verifyEmailNotice(org.springframework.security.core.Authentication auth, org.springframework.ui.Model model) {
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof com.vams.security.CustomUserDetails) {
                model.addAttribute("userEmail", ((com.vams.security.CustomUserDetails) principal).getUser().getEmail());
            }
        }
        return "verify-email-notice";
    }







    // Helper: Create a system log
    private void auditLog(String actor, String target, String action, String details) {
        SystemLog log = new SystemLog();
        log.setActorUsername(actor);
        log.setTargetUsername(target);
        log.setActionType(action);
        log.setDetails(details);
        systemLogRepository.save(log);
    }

    // Helper: Calculate live occupancy mathematically
    private long getLiveOccupancy() {
        long entries = scanLogRepository.findAll().stream().filter(l -> l.getScanType() == ScanType.ENTRY).count();
        long exits = scanLogRepository.findAll().stream().filter(l -> l.getScanType() == ScanType.EXIT).count();
        return Math.max(0, entries - exits);
    }

    // Helper: Determine if a user has been revoked
    private boolean isUserRevoked(User u) {
        if (u.getEmail() != null && u.getEmail().startsWith("REVOKED_")) {
            return true;
        }
        List<AccessRequest> reqs = requestRepository.findByUser(u);
        if (reqs.isEmpty()) {
            return false;
        }
        boolean hasActive = reqs.stream().anyMatch(r -> r.getStatus() == RequestStatus.APPROVED || r.getStatus() == RequestStatus.PENDING);
        if (hasActive) {
            return false;
        }
        return reqs.stream().anyMatch(r -> r.getStatus() == RequestStatus.REVOKED);
    }

    // Helper: Get all active (non-revoked) users
    private List<User> getActiveUsers() {
        return userRepository.findAll().stream()
                .filter(u -> !isUserRevoked(u))
                .collect(Collectors.toList());
    }

    // Helper: Get all revoked users
    private List<User> getRevokedUsers() {
        return userRepository.findAll().stream()
                .filter(this::isUserRevoked)
                .collect(Collectors.toList());
    }

    @GetMapping("/dashboard")
    public String routeDashboard(Authentication authentication) {
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).findFirst().orElse("ROLE_GUEST");
        if (role.equals("ROLE_OWNER")) return "redirect:/owner/dashboard";
        if (role.equals("ROLE_MANAGER")) return "redirect:/manager/dashboard";
        if (role.equals("ROLE_EMPLOYEE")) return "redirect:/employee/dashboard";
        return "redirect:/guest/dashboard";
    }

    // --- OWNER OVERVIEW (Dashboard) ---
    @GetMapping("/owner/dashboard")
    public String ownerDashboard(Model model) {
        List<User> activeUsers = getActiveUsers();
        List<User> revokedUsers = getRevokedUsers();

        long ownerCount = activeUsers.stream().filter(u -> u.getRole() == Role.OWNER).count();
        long mgrCount = activeUsers.stream().filter(u -> u.getRole() == Role.MANAGER).count();
        long empCount = activeUsers.stream().filter(u -> u.getRole() == Role.EMPLOYEE).count();
        long gstCount = activeUsers.stream().filter(u -> u.getRole() == Role.GUEST).count();
        long revokedCount = revokedUsers.size();
        long pendingReqCount = requestRepository.findAll().stream().filter(r -> r.getStatus() == RequestStatus.PENDING).count();

        model.addAttribute("occupancy", getLiveOccupancy());
        model.addAttribute("ownerCount", ownerCount);
        model.addAttribute("managerCount", mgrCount);
        model.addAttribute("employeeCount", empCount);
        model.addAttribute("guestCount", gstCount);
        model.addAttribute("revokedCount", revokedCount);
        model.addAttribute("pendingRequestsCount", pendingReqCount);
        return "owner-dashboard";
    }

    // --- OWNER: CREATE USER ---
    @GetMapping("/owner/create-user")
    public String ownerCreateUserView(Model model) {
        model.addAttribute("allGates", gateRepository.findAll());
        return "owner-create-user";
    }

    @PostMapping("/owner/create-user")
    public String createUser(
            @RequestParam String username, 
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam Role role,
            @RequestParam(required = false) Long gateId,
            @RequestParam ValidityType validityType) {
            
        // Prevent duplicate users/emails
        if (userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent()) {
            return "redirect:/owner/dashboard?error=UserExists";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setRole(role);
        newUser.setEmailVerified(false); // They still need to verify email
        userRepository.save(newUser);
        
        // Auto-approve their access request!
        AccessRequest request = new AccessRequest();
        request.setUser(newUser);
        request.setStatus(RequestStatus.APPROVED);
        request.setAccessLevel(AccessLevel.RESTRICTED); // Default RESTRICTED, could be parameter
        request.setValidityType(validityType);
        
        if (gateId != null) {
            Gate selectedGate = gateRepository.findById(gateId).orElse(null);
            request.setRequestedGate(selectedGate);
        }
        
        requestRepository.save(request);

        auditLog("admin", username, "ACCOUNT_CREATED", "Role: " + role + " via Owner Dashboard");

        return "redirect:/owner/create-user?userCreated=true&createdEmail=" + email;
    }

    // --- OWNER: ACCESS REQUESTS ---
    @GetMapping("/owner/requests")
    public String ownerRequestsView(Model model) {
        model.addAttribute("allRequests", requestRepository.findAll());
        return "owner-requests";
    }

    @PostMapping("/owner/approve/{id}")
    public String ownerApproveRequest(@PathVariable Long id, Authentication auth) {
        managerService.approveRequest(id, AccessLevel.RESTRICTED, ValidityType.ONGOING);
        AccessRequest req = requestRepository.findById(id).orElse(null);
        if (req != null) auditLog(auth.getName(), req.getUser().getUsername(), "REQUEST_APPROVED", "Approved by Owner");
        return "redirect:/owner/requests?approved";
    }

    @PostMapping("/owner/reject/{id}")
    public String ownerRejectRequest(@PathVariable Long id, Authentication auth) {
        managerService.rejectRequest(id);
        AccessRequest req = requestRepository.findById(id).orElse(null);
        if (req != null) auditLog(auth.getName(), req.getUser().getUsername(), "REQUEST_REJECTED", "Rejected by Owner");
        return "redirect:/owner/requests?rejected";
    }

    @PostMapping("/owner/suspend/{id}")
    public String ownerSuspendRequest(@PathVariable Long id, Authentication auth) {
        AccessRequest req = requestRepository.findById(id).orElse(null);
        if (req != null) {
            req.setStatus(RequestStatus.SUSPENDED);
            requestRepository.save(req);
            auditLog(auth.getName(), req.getUser().getUsername(), "ACCESS_SUSPENDED", "Suspended by Owner");
        }
        return "redirect:/owner/requests?suspended";
    }

    @PostMapping("/owner/reinstate/{id}")
    public String ownerReinstateRequest(@PathVariable Long id, Authentication auth) {
        AccessRequest req = requestRepository.findById(id).orElse(null);
        if (req != null) {
            req.setStatus(RequestStatus.APPROVED);
            requestRepository.save(req);
            auditLog(auth.getName(), req.getUser().getUsername(), "ACCESS_REINSTATED", "Reinstated by Owner");
        }
        return "redirect:/owner/requests?reinstated";
    }

    @PostMapping("/owner/revoke/{id}")
    public String ownerRevokeRequest(@PathVariable Long id, Authentication auth) {
        AccessRequest req = requestRepository.findById(id).orElse(null);
        if (req != null) {
            req.setStatus(RequestStatus.REVOKED);
            requestRepository.save(req);

            // Free up the email address so the user can re-register
            User user = req.getUser();
            String oldEmail = user.getEmail();
            user.setEmail("REVOKED_" + user.getId() + "_" + System.currentTimeMillis() + "@revoked.local");
            userRepository.save(user);

            auditLog(auth.getName(), user.getUsername(), "ACCESS_REVOKED", "Revoked permanently by Owner. Email freed up from: " + oldEmail);
        }
        return "redirect:/owner/requests?revoked";
    }

    // --- OWNER: SYSTEM USERS ---
    @GetMapping("/owner/users")
    public String ownerUsersView(Model model) {
        List<User> allUsers = userRepository.findAll();
        List<User> revokedUsers = getRevokedUsers();
        List<User> activeUsers = getActiveUsers();

        List<User> owners = activeUsers.stream()
                .filter(u -> u.getRole() == Role.OWNER)
                .collect(Collectors.toList());

        List<User> managers = activeUsers.stream()
                .filter(u -> u.getRole() == Role.MANAGER)
                .collect(Collectors.toList());

        List<User> employees = activeUsers.stream()
                .filter(u -> u.getRole() == Role.EMPLOYEE)
                .collect(Collectors.toList());

        List<User> guests = activeUsers.stream()
                .filter(u -> u.getRole() == Role.GUEST)
                .collect(Collectors.toList());

        model.addAttribute("allUsers", allUsers);
        model.addAttribute("owners", owners);
        model.addAttribute("managers", managers);
        model.addAttribute("employees", employees);
        model.addAttribute("guests", guests);
        model.addAttribute("revokedUsers", revokedUsers);

        return "owner-users";
    }

    @PostMapping("/owner/users/revoke/{id}")
    public String ownerRevokeUser(@PathVariable Long id, Authentication auth) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            String oldEmail = user.getEmail();
            if (oldEmail != null && !oldEmail.startsWith("REVOKED_")) {
                user.setEmail("REVOKED_" + user.getId() + "_" + System.currentTimeMillis() + "@revoked.local");
                userRepository.save(user);
            } else if (oldEmail == null) {
                user.setEmail("REVOKED_" + user.getId() + "_" + System.currentTimeMillis() + "@revoked.local");
                userRepository.save(user);
            }

            // Revoke all access requests associated with this user
            List<AccessRequest> reqs = requestRepository.findByUser(user);
            if (reqs.isEmpty()) {
                AccessRequest req = new AccessRequest();
                req.setUser(user);
                req.setStatus(RequestStatus.REVOKED);
                req.setAccessLevel(AccessLevel.RESTRICTED);
                req.setValidityType(ValidityType.ONGOING);
                requestRepository.save(req);
            } else {
                for (AccessRequest req : reqs) {
                    req.setStatus(RequestStatus.REVOKED);
                    requestRepository.save(req);
                }
            }

            auditLog(auth.getName(), user.getUsername(), "ACCESS_REVOKED", 
                     "Access revoked by Owner from System Users Master List. Former email: " + (oldEmail != null ? oldEmail : "N/A"));
        }
        return "redirect:/owner/users?revoked=true";
    }

    @PostMapping("/owner/users/reinstate/{id}")
    public String ownerReinstateUser(@PathVariable Long id, Authentication auth) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.getEmail() != null && user.getEmail().startsWith("REVOKED_")) {
                user.setEmail(user.getUsername() + "@reinstated.local");
                userRepository.save(user);
            }

            List<AccessRequest> reqs = requestRepository.findByUser(user);
            if (reqs.isEmpty()) {
                AccessRequest req = new AccessRequest();
                req.setUser(user);
                req.setStatus(RequestStatus.APPROVED);
                req.setAccessLevel(AccessLevel.RESTRICTED);
                req.setValidityType(ValidityType.ONGOING);
                requestRepository.save(req);
            } else {
                for (AccessRequest req : reqs) {
                    req.setStatus(RequestStatus.APPROVED);
                    requestRepository.save(req);
                }
            }

            auditLog(auth.getName(), user.getUsername(), "ACCESS_REINSTATED", 
                     "Access reinstated by Owner from System Users Master List.");
        }
        return "redirect:/owner/users?reinstated=true";
    }

    // --- OWNER: GATES ---
    @GetMapping("/owner/gates")
    public String ownerGatesView(Model model) {
        model.addAttribute("allGates", gateRepository.findAll());
        return "owner-gates";
    }

    @PostMapping("/owner/gates")
    public String createGate(@RequestParam String gateNo, 
                             @RequestParam String location, 
                             @RequestParam GatePermission permission) {
        Gate gate = new Gate();
        gate.setGateNo(gateNo);
        gate.setLocation(location);
        gate.setPermission(permission);
        gateRepository.save(gate);
        return "redirect:/owner/gates?success";
    }

    @PostMapping("/owner/gates/{id}/permission")
    public String updateGatePermission(@PathVariable Long id, @RequestParam GatePermission permission) {
        Gate gate = gateRepository.findById(id).orElse(null);
        if (gate != null) {
            gate.setPermission(permission);
            gateRepository.save(gate);
        }
        return "redirect:/owner/gates";
    }

    @PostMapping("/owner/gates/{id}/delete")
    public String deleteGate(@PathVariable Long id) {
        Gate gate = gateRepository.findById(id).orElse(null);
        if (gate != null) {
            // Remove foreign key references from AccessRequests before deleting
            for (AccessRequest req : requestRepository.findAll()) {
                if (req.getRequestedGate() != null && req.getRequestedGate().getId().equals(id)) {
                    req.setRequestedGate(null);
                    requestRepository.save(req);
                }
            }
            gateRepository.deleteById(id);
        }
        return "redirect:/owner/gates";
    }


    // --- OWNER: AUDIT LOGS ---
    @GetMapping("/owner/logs")
    public String ownerLogsView(Model model) {
        model.addAttribute("allLogs", systemLogRepository.findAllByOrderByTimestampDesc());
        return "owner-logs";
    }

    // Helper to check if user is approved
    private boolean isUserApproved(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return false;
        return requestRepository.findAll().stream()
                .anyMatch(r -> r.getUser().getId().equals(user.getId()) && r.getStatus() == RequestStatus.APPROVED);
    }

    // --- SHARED: HISTORY ---
    @GetMapping("/history")
    public String sharedHistory(Model model) {
        model.addAttribute("systemLogs", systemLogRepository.findAllByOrderByTimestampDesc());
        model.addAttribute("scanLogs", scanLogRepository.findAllByOrderByTimestampDesc());
        return "history";
    }

    // --- MANAGER: TODAY'S APPROVED ---
    @GetMapping("/manager/today-approved")
    public String todayApproved(Model model) {
        java.time.LocalDateTime todayStart = java.time.LocalDate.now().atStartOfDay();
        
        List<com.vams.model.SystemLog> todaySystem = systemLogRepository.findAllByOrderByTimestampDesc().stream()
            .filter(l -> l.getTimestamp().isAfter(todayStart))
            .filter(l -> l.getActionType().contains("APPROVED") || l.getActionType().contains("REJECTED") || l.getActionType().contains("REVOKED"))
            .toList();
            
        List<com.vams.model.ScanLog> todayScans = scanLogRepository.findAllByOrderByTimestampDesc().stream()
            .filter(l -> l.getTimestamp().isAfter(todayStart))
            .toList();

        model.addAttribute("systemLogs", todaySystem);
        model.addAttribute("scanLogs", todayScans);
        return "today-approved";
    }

    // --- MANAGER ---
    @GetMapping("/manager/dashboard")
    public String managerDashboard(Authentication auth, Model model) {
        if (!isUserApproved(auth.getName())) {
            return "pending-approval";
        }

        model.addAttribute("occupancy", getLiveOccupancy());
        
        List<User> activeUsers = getActiveUsers();
        List<User> revokedUsers = getRevokedUsers();

        long empCount = activeUsers.stream().filter(u -> u.getRole() == Role.EMPLOYEE).count();
        long mgrCount = activeUsers.stream().filter(u -> u.getRole() == Role.MANAGER).count();
        long gstCount = activeUsers.stream().filter(u -> u.getRole() == Role.GUEST).count();
        long revokedCount = revokedUsers.size();
        
        long pubAccess = requestRepository.findAll().stream()
                .filter(r -> r.getStatus() == RequestStatus.APPROVED && r.getAccessLevel() == AccessLevel.PUBLIC && !isUserRevoked(r.getUser()))
                .count();
        long resAccess = requestRepository.findAll().stream()
                .filter(r -> r.getStatus() == RequestStatus.APPROVED && r.getAccessLevel() == AccessLevel.RESTRICTED && !isUserRevoked(r.getUser()))
                .count();

        model.addAttribute("employeeCount", empCount);
        model.addAttribute("managerCount", mgrCount);
        model.addAttribute("guestCount", gstCount);
        model.addAttribute("revokedCount", revokedCount);
        model.addAttribute("publicAccessCount", pubAccess);
        model.addAttribute("restrictedAccessCount", resAccess);
        
        return "manager-dashboard";
    }

    @Autowired private com.vams.repository.GuardStationRequestRepository stationRequestRepository;

    @GetMapping("/manager/requests")
    public String pendingRequests(Authentication auth, Model model) {
        if (!isUserApproved(auth.getName())) {
            return "pending-approval";
        }
        
        // Manager can only approve GUEST and EMPLOYEE, not other Managers.
        List<AccessRequest> pending = managerService.getPendingRequests().stream()
                .filter(r -> r.getUser().getRole() != Role.MANAGER && r.getUser().getRole() != Role.OWNER)
                .toList();
                
        List<com.vams.model.GuardStationRequest> pendingStations = stationRequestRepository.findByStatus(RequestStatus.PENDING);
                
        model.addAttribute("requests", pending);
        model.addAttribute("stationRequests", pendingStations);
        return "pending-requests";
    }

    @PostMapping("/manager/approve-station/{id}")
    public String approveStationRequest(@PathVariable Long id, Authentication auth) {
        com.vams.model.GuardStationRequest req = stationRequestRepository.findById(id).orElse(null);
        if (req != null) {
            req.setStatus(RequestStatus.APPROVED);
            stationRequestRepository.save(req);
            auditLog(auth.getName(), req.getUser().getUsername(), "STATION_APPROVED", "Approved Station at Gate " + req.getGate().getGateNo());
        }
        return "redirect:/manager/requests?approved";
    }

    @PostMapping("/manager/reject-station/{id}")
    public String rejectStationRequest(@PathVariable Long id, Authentication auth) {
        com.vams.model.GuardStationRequest req = stationRequestRepository.findById(id).orElse(null);
        if (req != null) {
            req.setStatus(RequestStatus.REJECTED);
            stationRequestRepository.save(req);
            auditLog(auth.getName(), req.getUser().getUsername(), "STATION_REJECTED", "Rejected Station at Gate " + req.getGate().getGateNo());
        }
        return "redirect:/manager/requests?rejected";
    }

    @PostMapping("/manager/approve/{id}")
    public String approveRequest(@PathVariable Long id, Authentication auth) {
        managerService.approveRequest(id, AccessLevel.PUBLIC, ValidityType.ONGOING);
        AccessRequest req = requestRepository.findById(id).orElse(null);
        if (req != null) auditLog(auth.getName(), req.getUser().getUsername(), "REQUEST_APPROVED", "Approved by Manager");
        return "redirect:/manager/requests?approved";
    }

    @PostMapping("/manager/reject/{id}")
    public String rejectRequest(@PathVariable Long id, Authentication auth) {
        managerService.rejectRequest(id);
        AccessRequest req = requestRepository.findById(id).orElse(null);
        if (req != null) auditLog(auth.getName(), req.getUser().getUsername(), "REQUEST_REJECTED", "Rejected by Manager");
        return "redirect:/manager/requests?rejected";
    }

    // --- GUEST / EMPLOYEE ---
    @GetMapping("/guest/dashboard")
    public String guestDashboard(Authentication auth, Model model) {
        if (!isUserApproved(auth.getName())) {
            return "pending-approval";
        }
        
        User user = userRepository.findByUsername(auth.getName()).orElse(null);
        if (user != null) {
            AccessRequest req = requestRepository.findAll().stream()
                    .filter(r -> r.getUser().getId().equals(user.getId()) && r.getStatus() == RequestStatus.APPROVED)
                    .findFirst().orElse(null);
            
            if (req != null && req.getQrToken() != null) {
                try {
                    // Generate actual PNG QR Code as Base64 for the frontend
                    String qrBase64 = qrService.generateQRCodeImageBase64(req.getQrToken());
                    model.addAttribute("qrImage", qrBase64);
                    model.addAttribute("request", req);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "guest-dashboard";
    }

    private String resolveHomeRouteForRole(Role role) {
        if (Role.OWNER.equals(role)) return "redirect:/owner/dashboard";
        if (Role.MANAGER.equals(role)) return "redirect:/manager/dashboard";
        if (Role.EMPLOYEE.equals(role)) return "redirect:/employee/dashboard";
        return "redirect:/guest/dashboard";
    }


    private String resolveHomeRouteForRole(Role role) {
        if (Role.OWNER.equals(role)) return "redirect:/owner/dashboard";
        if (Role.MANAGER.equals(role)) return "redirect:/manager/dashboard";
        if (Role.EMPLOYEE.equals(role)) return "redirect:/employee/dashboard";
        return "redirect:/guest/dashboard";
    }


    private String resolveHomeRouteForRole(Role role) {
        if (Role.OWNER.equals(role)) return "redirect:/owner/dashboard";
        if (Role.MANAGER.equals(role)) return "redirect:/manager/dashboard";
        if (Role.EMPLOYEE.equals(role)) return "redirect:/employee/dashboard";
        return "redirect:/guest/dashboard";
    }


    private String resolveHomeRouteForRole(Role role) {
        if (Role.OWNER.equals(role)) return "redirect:/owner/dashboard";
        if (Role.MANAGER.equals(role)) return "redirect:/manager/dashboard";
        if (Role.EMPLOYEE.equals(role)) return "redirect:/employee/dashboard";
        return "redirect:/guest/dashboard";
    }


    private String resolveHomeRouteForRole(Role role) {
        if (Role.OWNER.equals(role)) return "redirect:/owner/dashboard";
        if (Role.MANAGER.equals(role)) return "redirect:/manager/dashboard";
        if (Role.EMPLOYEE.equals(role)) return "redirect:/employee/dashboard";
        return "redirect:/guest/dashboard";
    }

}
