package com.vams.config;

import com.vams.model.*;
import com.vams.repository.AccessRequestRepository;
import com.vams.repository.GateRepository;
import com.vams.repository.GuardStationRequestRepository;
import com.vams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessRequestRepository accessRequestRepository;

    @Autowired
    private GuardStationRequestRepository stationRequestRepository;

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Ensure Default Gates exist
        Gate gate1 = ensureGate("G-01", "Main Campus Entrance", GatePermission.GENERAL);
        Gate gate2 = ensureGate("G-02", "North Wing Turnstile", GatePermission.GENERAL);

        // 2. Initialize / Update Demo Accounts as requested:
        // Owner (Super - Admin): admin / 321654
        User admin = createOrUpdateUser("admin", "321654", Role.OWNER, "System Owner", "admin@vams.local");
        ensureAccessRequest(admin, AccessLevel.RESTRICTED, gate1);

        // Manager (Admin): manager / 98765
        User manager = createOrUpdateUser("manager", "98765", Role.MANAGER, "Operations Manager", "manager@vams.local");
        ensureAccessRequest(manager, AccessLevel.RESTRICTED, gate1);

        // Employee / Guard 1: employee1 / 654123
        User emp1 = createOrUpdateUser("employee1", "654123", Role.EMPLOYEE, "Security Guard 1", "employee1@vams.local");
        ensureAccessRequest(emp1, AccessLevel.RESTRICTED, gate1);
        ensureStationRequest(emp1, gate1);

        // Employee / Guard 2: employee2 / 53214
        User emp2 = createOrUpdateUser("employee2", "53214", Role.EMPLOYEE, "Security Guard 2", "employee2@vams.local");
        ensureAccessRequest(emp2, AccessLevel.RESTRICTED, gate2);
        ensureStationRequest(emp2, gate2);

        // Visitor / Guest: guest1 / 123456
        User guest1 = createOrUpdateUser("guest1", "123456", Role.GUEST, "Visitor Guest", "guest1@vams.local");
        ensureAccessRequest(guest1, AccessLevel.PUBLIC, gate1);

        System.out.println("=========================================");
        System.out.println("✓ VAMS Demo Accounts Initialized & Verified:");
        System.out.println("  1. Owner:      admin     (Role: OWNER)");
        System.out.println("  2. Manager:    manager   (Role: MANAGER)");
        System.out.println("  3. Guard 1:    employee1 (Role: EMPLOYEE)");
        System.out.println("  4. Guard 2:    employee2 (Role: EMPLOYEE)");
        System.out.println("  5. Guest:      guest1    (Role: GUEST)");
        System.out.println("=========================================");
    }

    private Gate ensureGate(String gateNo, String location, GatePermission permission) {
        return gateRepository.findAll().stream()
                .filter(g -> g.getGateNo() != null && g.getGateNo().equalsIgnoreCase(gateNo))
                .findFirst()
                .orElseGet(() -> {
                    Gate g = new Gate();
                    g.setGateNo(gateNo);
                    g.setLocation(location);
                    g.setPermission(permission);
                    return gateRepository.save(g);
                });
    }

    private User createOrUpdateUser(String username, String plainPassword, Role role, String name, String email) {
        User user = userRepository.findByUsername(username).orElseGet(() -> {
            User byEmail = userRepository.findByEmail(email).orElse(null);
            if (byEmail != null) return byEmail;
            return new User();
        });
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(plainPassword));
        user.setRole(role);
        user.setName(name);
        user.setEmail(email);
        user.setEmailVerified(true);
        return userRepository.save(user);
    }

    private void ensureAccessRequest(User user, AccessLevel level, Gate gate) {
        List<AccessRequest> existing = accessRequestRepository.findByUser(user);
        AccessRequest req = existing.stream()
                .filter(r -> r.getStatus() == RequestStatus.APPROVED)
                .findFirst().orElse(null);
        if (req == null) {
            req = existing.isEmpty() ? new AccessRequest() : existing.get(0);
            req.setUser(user);
            req.setStatus(RequestStatus.APPROVED);
            req.setAccessLevel(level);
            req.setValidityType(ValidityType.ONGOING);
            if (req.getQrToken() == null || req.getQrToken().isEmpty()) {
                req.setQrToken(UUID.randomUUID().toString());
            }
            req.setApprovedAt(LocalDateTime.now());
            req.setRequestedGate(gate);
            accessRequestRepository.save(req);
        }
    }

    private void ensureStationRequest(User user, Gate gate) {
        List<GuardStationRequest> existing = stationRequestRepository.findByUser(user);
        GuardStationRequest stationReq = existing.stream()
                .filter(r -> r.getStatus() == RequestStatus.APPROVED)
                .findFirst().orElse(null);
        if (stationReq == null) {
            stationReq = existing.isEmpty() ? new GuardStationRequest() : existing.get(0);
            stationReq.setUser(user);
            stationReq.setGate(gate);
            stationReq.setStatus(RequestStatus.APPROVED);
            stationReq.setRequestedAt(LocalDateTime.now());
            stationRequestRepository.save(stationReq);
        }
    }

    private User seedUserAccount(String username, String rawPassword, Role role, String name, String email) {
        return createOrUpdateUser(username, rawPassword, role, name, email);
    }


    private User seedUserAccount(String username, String rawPassword, Role role, String name, String email) {
        return createOrUpdateUser(username, rawPassword, role, name, email);
    }


    private User seedUserAccount(String username, String rawPassword, Role role, String name, String email) {
        return createOrUpdateUser(username, rawPassword, role, name, email);
    }


    private User seedUserAccount(String username, String rawPassword, Role role, String name, String email) {
        return createOrUpdateUser(username, rawPassword, role, name, email);
    }


    private User seedUserAccount(String username, String rawPassword, Role role, String name, String email) {
        return createOrUpdateUser(username, rawPassword, role, name, email);
    }


    private User seedUserAccount(String username, String rawPassword, Role role, String name, String email) {
        return createOrUpdateUser(username, rawPassword, role, name, email);
    }


    private User seedUserAccount(String username, String rawPassword, Role role, String name, String email) {
        return createOrUpdateUser(username, rawPassword, role, name, email);
    }

}
