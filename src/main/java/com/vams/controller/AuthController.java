package com.vams.controller;

import com.vams.model.*;
import com.vams.repository.UserRepository;
import com.vams.repository.AccessRequestRepository;
import com.vams.repository.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired private UserRepository userRepository;
    @Autowired private AccessRequestRepository requestRepository;
    @Autowired private GateRepository gateRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping({"/", "/home"}) public String homePage() { return "home"; }
    @GetMapping("/login") public String loginPage() { return "login"; }
    @GetMapping("/register") public String registerPage(Model model) { 
        model.addAttribute("user", new User()); 
        model.addAttribute("allGates", gateRepository.findAll());
        return "register"; 
    }
    @GetMapping("/verify") public String verifyPage() { return "verify"; }
    @GetMapping("/forgot-password") public String forgotPasswordPage() { return "forgot-password"; }

    @PostMapping("/register") 
    public String registerUser(User user, Model model, @org.springframework.web.bind.annotation.RequestParam(required = false) Long gateId) {
        // Prevent 500 Error if the username already exists!
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "User ID / Name already exists! Please choose another.");
            model.addAttribute("allGates", gateRepository.findAll());
            return "register";
        }
        
        // Prevent duplicate emails
        if (user.getEmail() != null && userRepository.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email already exists! Please use another.");
            model.addAttribute("allGates", gateRepository.findAll());
            return "register";
        }

        // Prevent duplicate mobile numbers - removed as per request
        if (user.getRole() == null) { user.setRole(Role.GUEST); }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        // Fix Bug 5: Generate a PENDING request for Manager to see
        AccessRequest request = new AccessRequest();
        request.setUser(user);
        request.setStatus(RequestStatus.PENDING);
        request.setAccessLevel(AccessLevel.PUBLIC);
        request.setValidityType(ValidityType.ONGOING);
        
        if (gateId != null) {
            Gate selectedGate = gateRepository.findById(gateId).orElse(null);
            request.setRequestedGate(selectedGate);
        }

        requestRepository.save(request);

        // Redirect to verify page with email parameter for auto-sending
        return "redirect:/verify?email=" + user.getEmail();
    }

    @PostMapping("/reset-password")
    public String resetPassword(@org.springframework.web.bind.annotation.RequestParam String email, 
                                @org.springframework.web.bind.annotation.RequestParam String newPassword) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }
        return "redirect:/login?passwordReset=true";
    }

    @PostMapping("/api/verify-success")
    @org.springframework.web.bind.annotation.ResponseBody
    public String verifySuccess(@org.springframework.web.bind.annotation.RequestParam String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            user.setEmailVerified(true);
            userRepository.save(user);
            return "success";
        }
        return "error";
    }

    @org.springframework.web.bind.annotation.GetMapping("/api/health")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, String> getApplicationHealthCheck() {
        java.util.Map<String, String> status = new java.util.HashMap<>();
        status.put("status", "UP");
        status.put("uptime", java.time.Instant.now().toString());
        return status;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/health")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, String> getApplicationHealthCheck() {
        java.util.Map<String, String> status = new java.util.HashMap<>();
        status.put("status", "UP");
        status.put("uptime", java.time.Instant.now().toString());
        return status;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/health")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, String> getApplicationHealthCheck() {
        java.util.Map<String, String> status = new java.util.HashMap<>();
        status.put("status", "UP");
        status.put("uptime", java.time.Instant.now().toString());
        return status;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/health")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, String> getApplicationHealthCheck() {
        java.util.Map<String, String> status = new java.util.HashMap<>();
        status.put("status", "UP");
        status.put("uptime", java.time.Instant.now().toString());
        return status;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/health")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, String> getApplicationHealthCheck() {
        java.util.Map<String, String> status = new java.util.HashMap<>();
        status.put("status", "UP");
        status.put("uptime", java.time.Instant.now().toString());
        return status;
    }

}
