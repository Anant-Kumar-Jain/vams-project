package com.vams.controller;

import com.vams.model.Gate;
import com.vams.model.GuardStationRequest;
import com.vams.model.RequestStatus;
import com.vams.model.User;
import com.vams.repository.GateRepository;
import com.vams.repository.GuardStationRequestRepository;
import com.vams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class EmployeeController {

    @Autowired private UserRepository userRepository;
    @Autowired private GateRepository gateRepository;
    @Autowired private GuardStationRequestRepository stationRequestRepository;

    @GetMapping("/employee/dashboard")
    public String employeeDashboard(Authentication auth, Model model) {
        User user = userRepository.findByUsername(auth.getName()).orElse(null);
        if (user == null) return "redirect:/login";
        
        // Find their station request
        GuardStationRequest activeRequest = stationRequestRepository.findByUser(user).stream()
                .findFirst().orElse(null);
                
        model.addAttribute("gates", gateRepository.findAll());
        model.addAttribute("stationRequest", activeRequest);
        
        return "employee-dashboard";
    }

    @PostMapping("/employee/request-station")
    public String requestStation(@RequestParam Long gateId, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElse(null);
        Gate gate = gateRepository.findById(gateId).orElse(null);
        
        if (user != null && gate != null) {
            // Check if they already have one
            GuardStationRequest existing = stationRequestRepository.findByUser(user).stream().findFirst().orElse(null);
            if (existing != null) {
                stationRequestRepository.delete(existing); // Override old request
            }
            
            GuardStationRequest newReq = new GuardStationRequest();
            newReq.setUser(user);
            newReq.setGate(gate);
            newReq.setStatus(RequestStatus.PENDING);
            newReq.setRequestedAt(LocalDateTime.now());
            stationRequestRepository.save(newReq);
        }
        
        return "redirect:/employee/dashboard?success";
    }

    @org.springframework.web.bind.annotation.GetMapping("/guard/station-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public boolean isGuardStationAssigned(Long guardId) {
        return stationRequestRepository.existsByGuardIdAndStationStatus(guardId, RequestStatus.APPROVED);
    }


    @org.springframework.web.bind.annotation.GetMapping("/guard/station-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public boolean isGuardStationAssigned(Long guardId) {
        return stationRequestRepository.existsByGuardIdAndStationStatus(guardId, RequestStatus.APPROVED);
    }


    @org.springframework.web.bind.annotation.GetMapping("/guard/station-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public boolean isGuardStationAssigned(Long guardId) {
        return stationRequestRepository.existsByGuardIdAndStationStatus(guardId, RequestStatus.APPROVED);
    }


    @org.springframework.web.bind.annotation.GetMapping("/guard/station-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public boolean isGuardStationAssigned(Long guardId) {
        return stationRequestRepository.existsByGuardIdAndStationStatus(guardId, RequestStatus.APPROVED);
    }


    @org.springframework.web.bind.annotation.GetMapping("/guard/station-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public boolean isGuardStationAssigned(Long guardId) {
        return stationRequestRepository.existsByGuardIdAndStationStatus(guardId, RequestStatus.APPROVED);
    }


    @org.springframework.web.bind.annotation.GetMapping("/guard/station-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public boolean isGuardStationAssigned(Long guardId) {
        return stationRequestRepository.existsByGuardIdAndStationStatus(guardId, RequestStatus.APPROVED);
    }

}
