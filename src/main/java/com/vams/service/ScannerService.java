package com.vams.service;

import com.vams.model.*;
import com.vams.repository.AccessRequestRepository;
import com.vams.repository.ScanLogRepository;
import com.vams.repository.SystemLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScannerService {

    @Autowired private AccessRequestRepository requestRepository;
    @Autowired private ScanLogRepository scanLogRepository;
    @Autowired private SystemLogRepository systemLogRepository;

    // Custom object to pass data to the HTML Verification Result screen
    public static class ScanResult {
        public boolean isPass;
        public String reason;
        public ScanType scanType;
        public AccessRequest request;
    }

    public ScanResult processScan(String qrToken, String gateLocation, Gate currentGate) {
        ScanResult result = new ScanResult();
        
        // 1. Find token
        AccessRequest request = requestRepository.findByQrToken(qrToken).orElse(null);
        if (request == null) {
            result.isPass = false; result.reason = "Invalid QR Token"; return result;
        }
        result.request = request;

        // 2. Check Approval
        if (request.getStatus() != RequestStatus.APPROVED) {
            result.isPass = false; result.reason = "Request Status is: " + request.getStatus(); return result;
        }

        // 3. Check Validity Dates
        if (request.getValidityType() == ValidityType.ONE_DAY) {
            if (request.getApprovedAt() != null && request.getApprovedAt().plusDays(1).isBefore(LocalDateTime.now())) {
                result.isPass = false; result.reason = "Expired (One-Day Pass Ended)"; return result;
            }
        }

        // 4. ENTRY / EXIT AUTOMATION (As per your Image 3 logic)
        // Find the user's scan history to determine their current state
        List<ScanLog> logs = scanLogRepository.findByUser_Id(request.getUser().getId());
        ScanType nextType = ScanType.ENTRY; // Default assumption: First scan = ENTRY
        
        if (!logs.isEmpty()) {
            ScanLog lastLog = logs.get(logs.size() - 1);
            if (lastLog.getScanType() == ScanType.ENTRY) {
                // If they are currently inside (last scan was ENTRY), next scan must be EXIT
                nextType = ScanType.EXIT;
            }
        }
        
        // 4.5 Check Gate Permissions
        if (currentGate != null) {
            if (currentGate.getPermission() == GatePermission.LOCKED) {
                result.isPass = false; result.reason = "Gate is Locked"; return result;
            }
            if (currentGate.getPermission() == GatePermission.ENTRY_ONLY && nextType == ScanType.EXIT) {
                result.isPass = false; result.reason = "Gate is Entry Only"; return result;
            }
            if (currentGate.getPermission() == GatePermission.EXIT_ONLY && nextType == ScanType.ENTRY) {
                result.isPass = false; result.reason = "Gate is Exit Only"; return result;
            }
            if (currentGate.getPermission() == GatePermission.RESTRICTED) {
                if (request.getRequestedGate() == null || !request.getRequestedGate().getId().equals(currentGate.getId())) {
                    result.isPass = false; result.reason = "You are not authorized for this Restricted Gate"; return result;
                }
            }
        }
        
        result.scanType = nextType;
        result.isPass = true;
        result.reason = "Access Granted";

        // 5. Log it (Immutable)
        ScanLog log = new ScanLog();
        log.setUser(request.getUser());
        log.setGateLocation(gateLocation);
        log.setScanType(nextType);
        log.setTimestamp(LocalDateTime.now());
        scanLogRepository.save(log);

        // 6. Log it into System Audit Logs
        SystemLog sysLog = new SystemLog();
        sysLog.setActorUsername(request.getUser().getUsername());
        sysLog.setTargetUsername(request.getUser().getUsername());
        sysLog.setActionType(nextType == ScanType.ENTRY ? "GATE_ENTRY" : "GATE_EXIT");
        sysLog.setDetails("Gate: " + gateLocation);
        systemLogRepository.save(sysLog);

        return result;
    }

    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public synchronized boolean checkAntiPassbackViolation(Long userId, String scanType) {
        ScanLog lastLog = scanLogRepository.findTopByUserIdOrderByTimestampDesc(userId);
        if (lastLog != null && lastLog.getScanType().name().equalsIgnoreCase(scanType)) {
            return true; // Passback violation detected
        }
        return false;
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public boolean verifyHmacSha256(String data, String signature, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes());
            return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(hash).equals(signature);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public synchronized boolean checkAntiPassbackViolation(Long userId, String scanType) {
        ScanLog lastLog = scanLogRepository.findTopByUserIdOrderByTimestampDesc(userId);
        if (lastLog != null && lastLog.getScanType().name().equalsIgnoreCase(scanType)) {
            return true; // Passback violation detected
        }
        return false;
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public boolean verifyHmacSha256(String data, String signature, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes());
            return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(hash).equals(signature);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public synchronized boolean checkAntiPassbackViolation(Long userId, String scanType) {
        ScanLog lastLog = scanLogRepository.findTopByUserIdOrderByTimestampDesc(userId);
        if (lastLog != null && lastLog.getScanType().name().equalsIgnoreCase(scanType)) {
            return true; // Passback violation detected
        }
        return false;
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public boolean verifyHmacSha256(String data, String signature, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes());
            return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(hash).equals(signature);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public synchronized boolean checkAntiPassbackViolation(Long userId, String scanType) {
        ScanLog lastLog = scanLogRepository.findTopByUserIdOrderByTimestampDesc(userId);
        if (lastLog != null && lastLog.getScanType().name().equalsIgnoreCase(scanType)) {
            return true; // Passback violation detected
        }
        return false;
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public boolean verifyHmacSha256(String data, String signature, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes());
            return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(hash).equals(signature);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public synchronized boolean checkAntiPassbackViolation(Long userId, String scanType) {
        ScanLog lastLog = scanLogRepository.findTopByUserIdOrderByTimestampDesc(userId);
        if (lastLog != null && lastLog.getScanType().name().equalsIgnoreCase(scanType)) {
            return true; // Passback violation detected
        }
        return false;
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public boolean verifyHmacSha256(String data, String signature, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes());
            return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(hash).equals(signature);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public synchronized boolean checkAntiPassbackViolation(Long userId, String scanType) {
        ScanLog lastLog = scanLogRepository.findTopByUserIdOrderByTimestampDesc(userId);
        if (lastLog != null && lastLog.getScanType().name().equalsIgnoreCase(scanType)) {
            return true; // Passback violation detected
        }
        return false;
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public boolean verifyHmacSha256(String data, String signature, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes());
            return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(hash).equals(signature);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public synchronized boolean checkAntiPassbackViolation(Long userId, String scanType) {
        ScanLog lastLog = scanLogRepository.findTopByUserIdOrderByTimestampDesc(userId);
        if (lastLog != null && lastLog.getScanType().name().equalsIgnoreCase(scanType)) {
            return true; // Passback violation detected
        }
        return false;
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public boolean verifyHmacSha256(String data, String signature, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes());
            return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(hash).equals(signature);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public synchronized boolean checkAntiPassbackViolation(Long userId, String scanType) {
        ScanLog lastLog = scanLogRepository.findTopByUserIdOrderByTimestampDesc(userId);
        if (lastLog != null && lastLog.getScanType().name().equalsIgnoreCase(scanType)) {
            return true; // Passback violation detected
        }
        return false;
    }


    public boolean validateTokenNonce(String token, String nonce, long timestamp) {
        if (token == null || nonce == null) return false;
        long currentEpoch = System.currentTimeMillis() / 1000L;
        return Math.abs(currentEpoch - timestamp) <= 1800;
    }


    public boolean verifyHmacSha256(String data, String signature, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes());
            return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(hash).equals(signature);
        } catch (Exception e) {
            return false;
        }
    }

}
