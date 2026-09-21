package com.vams.controller;

import com.vams.service.ScannerService;
import com.vams.repository.GateRepository;
import com.vams.model.Gate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/scanner")
public class ScannerController {

    @Autowired private ScannerService scannerService;
    @Autowired private GateRepository gateRepository;

    // Show the Scanner Camera UI with specific gate pre-selected
    @GetMapping("/{id}")
    public String scannerPageWithGate(@PathVariable Long id, Model model) {
        model.addAttribute("allGates", gateRepository.findAll());
        model.addAttribute("selectedGateId", id);
        return "scanner";
    }

    // Show the Scanner Camera UI
    @GetMapping
    public String scannerPage(Model model) {
        model.addAttribute("allGates", gateRepository.findAll());
        return "scanner";
    }

    // Process the scanned QR code or manually typed token
    @PostMapping("/verify")
    public String verifyQr(@RequestParam("qrToken") String qrToken, @RequestParam("gateId") Long gateId, Model model) {
        Gate gate = gateRepository.findById(gateId).orElse(null);
        String gateLocation = (gate != null) ? gate.getGateNo() + " - " + gate.getLocation() : "Unknown Gate";
        ScannerService.ScanResult result = scannerService.processScan(qrToken, gateLocation, gate);
        model.addAttribute("result", result);
        return "scan-result";
    }

    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }


    @org.springframework.web.bind.annotation.GetMapping("/api/gate/live-status")
    @org.springframework.web.bind.annotation.ResponseBody
    public java.util.Map<String, Object> getLiveGateTelemetry() {
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("timestamp", java.time.LocalDateTime.now());
        data.put("status", "OPERATIONAL");
        return data;
    }

}
