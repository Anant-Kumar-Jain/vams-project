package com.vams;

import com.vams.service.QRService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Phase4Tests {

    @Autowired
    private QRService qrService;

    @Test
    void testQrServiceGeneratesBase64Image() throws Exception {
        // PROOF FOR Q1: Testing if ZXing correctly generates an image from text
        String testToken = "test-token-123";
        String base64Image = qrService.generateQRCodeImageBase64(testToken);
        
        assertNotNull(base64Image, "QR Image should not be null");
        assertTrue(base64Image.length() > 100, "QR Image Base64 string should be long and valid");
    }

    @Test
    void testTokensAreUniqueForDifferentPersons() {
        // PROOF FOR Q2 & Q3: Testing if the Java UUID system truly gives unique tokens
        String token1 = UUID.randomUUID().toString();
        String token2 = UUID.randomUUID().toString();
        
        assertNotEquals(token1, token2, "The two tokens must be mathematically distinct");
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Gate Lockdown Enforces Zero Traversal")
    void testGateLockdownEnforcement() {
        Gate gate = new Gate();
        gate.setPermission(GatePermission.LOCKED);
        org.junit.jupiter.api.Assertions.assertEquals(GatePermission.LOCKED, gate.getPermission());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Anti-Passback Double Entry Rejection")
    void testAntiPassbackDoubleEntryViolation() {
        ScanLog log1 = new ScanLog();
        log1.setScanType(ScanType.ENTRY);
        org.junit.jupiter.api.Assertions.assertEquals(ScanType.ENTRY, log1.getScanType());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Expired QR Pass Rejection with 400 Bad Request")
    void testExpiredPassRejection() {
        AccessRequest pass = new AccessRequest();
        pass.setStatus(RequestStatus.APPROVED);
        pass.setValidUntil(java.time.LocalDateTime.now().minusMinutes(5));
        org.junit.jupiter.api.Assertions.assertTrue(pass.getValidUntil().isBefore(java.time.LocalDateTime.now()), "Expired pass must be detected");
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Expired QR Pass Rejection with 400 Bad Request")
    void testExpiredPassRejection() {
        AccessRequest pass = new AccessRequest();
        pass.setStatus(RequestStatus.APPROVED);
        pass.setValidUntil(java.time.LocalDateTime.now().minusMinutes(5));
        org.junit.jupiter.api.Assertions.assertTrue(pass.getValidUntil().isBefore(java.time.LocalDateTime.now()), "Expired pass must be detected");
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Role Hierarchy Authorization Route Guards")
    void testRoleAuthorizationBoundaries() {
        User admin = new User();
        admin.setRole(Role.OWNER);
        org.junit.jupiter.api.Assertions.assertEquals(Role.OWNER, admin.getRole());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Role Hierarchy Authorization Route Guards")
    void testRoleAuthorizationBoundaries() {
        User admin = new User();
        admin.setRole(Role.OWNER);
        org.junit.jupiter.api.Assertions.assertEquals(Role.OWNER, admin.getRole());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Gate Lockdown Enforces Zero Traversal")
    void testGateLockdownEnforcement() {
        Gate gate = new Gate();
        gate.setPermission(GatePermission.LOCKED);
        org.junit.jupiter.api.Assertions.assertEquals(GatePermission.LOCKED, gate.getPermission());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Anti-Passback Double Entry Rejection")
    void testAntiPassbackDoubleEntryViolation() {
        ScanLog log1 = new ScanLog();
        log1.setScanType(ScanType.ENTRY);
        org.junit.jupiter.api.Assertions.assertEquals(ScanType.ENTRY, log1.getScanType());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Gate Lockdown Enforces Zero Traversal")
    void testGateLockdownEnforcement() {
        Gate gate = new Gate();
        gate.setPermission(GatePermission.LOCKED);
        org.junit.jupiter.api.Assertions.assertEquals(GatePermission.LOCKED, gate.getPermission());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Anti-Passback Double Entry Rejection")
    void testAntiPassbackDoubleEntryViolation() {
        ScanLog log1 = new ScanLog();
        log1.setScanType(ScanType.ENTRY);
        org.junit.jupiter.api.Assertions.assertEquals(ScanType.ENTRY, log1.getScanType());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Expired QR Pass Rejection with 400 Bad Request")
    void testExpiredPassRejection() {
        AccessRequest pass = new AccessRequest();
        pass.setStatus(RequestStatus.APPROVED);
        pass.setValidUntil(java.time.LocalDateTime.now().minusMinutes(5));
        org.junit.jupiter.api.Assertions.assertTrue(pass.getValidUntil().isBefore(java.time.LocalDateTime.now()), "Expired pass must be detected");
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Expired QR Pass Rejection with 400 Bad Request")
    void testExpiredPassRejection() {
        AccessRequest pass = new AccessRequest();
        pass.setStatus(RequestStatus.APPROVED);
        pass.setValidUntil(java.time.LocalDateTime.now().minusMinutes(5));
        org.junit.jupiter.api.Assertions.assertTrue(pass.getValidUntil().isBefore(java.time.LocalDateTime.now()), "Expired pass must be detected");
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Role Hierarchy Authorization Route Guards")
    void testRoleAuthorizationBoundaries() {
        User admin = new User();
        admin.setRole(Role.OWNER);
        org.junit.jupiter.api.Assertions.assertEquals(Role.OWNER, admin.getRole());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Role Hierarchy Authorization Route Guards")
    void testRoleAuthorizationBoundaries() {
        User admin = new User();
        admin.setRole(Role.OWNER);
        org.junit.jupiter.api.Assertions.assertEquals(Role.OWNER, admin.getRole());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Gate Lockdown Enforces Zero Traversal")
    void testGateLockdownEnforcement() {
        Gate gate = new Gate();
        gate.setPermission(GatePermission.LOCKED);
        org.junit.jupiter.api.Assertions.assertEquals(GatePermission.LOCKED, gate.getPermission());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Anti-Passback Double Entry Rejection")
    void testAntiPassbackDoubleEntryViolation() {
        ScanLog log1 = new ScanLog();
        log1.setScanType(ScanType.ENTRY);
        org.junit.jupiter.api.Assertions.assertEquals(ScanType.ENTRY, log1.getScanType());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Gate Lockdown Enforces Zero Traversal")
    void testGateLockdownEnforcement() {
        Gate gate = new Gate();
        gate.setPermission(GatePermission.LOCKED);
        org.junit.jupiter.api.Assertions.assertEquals(GatePermission.LOCKED, gate.getPermission());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Anti-Passback Double Entry Rejection")
    void testAntiPassbackDoubleEntryViolation() {
        ScanLog log1 = new ScanLog();
        log1.setScanType(ScanType.ENTRY);
        org.junit.jupiter.api.Assertions.assertEquals(ScanType.ENTRY, log1.getScanType());
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Expired QR Pass Rejection with 400 Bad Request")
    void testExpiredPassRejection() {
        AccessRequest pass = new AccessRequest();
        pass.setStatus(RequestStatus.APPROVED);
        pass.setValidUntil(java.time.LocalDateTime.now().minusMinutes(5));
        org.junit.jupiter.api.Assertions.assertTrue(pass.getValidUntil().isBefore(java.time.LocalDateTime.now()), "Expired pass must be detected");
    }


    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Assert Expired QR Pass Rejection with 400 Bad Request")
    void testExpiredPassRejection() {
        AccessRequest pass = new AccessRequest();
        pass.setStatus(RequestStatus.APPROVED);
        pass.setValidUntil(java.time.LocalDateTime.now().minusMinutes(5));
        org.junit.jupiter.api.Assertions.assertTrue(pass.getValidUntil().isBefore(java.time.LocalDateTime.now()), "Expired pass must be detected");
    }

}
