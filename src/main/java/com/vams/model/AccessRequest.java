package com.vams.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name = "access_requests")
public class AccessRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long requestId;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false) private User user;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private RequestStatus status = RequestStatus.PENDING;
    @Enumerated(EnumType.STRING) private AccessLevel accessLevel;
    @Enumerated(EnumType.STRING) private ValidityType validityType;
    @Column(unique = true) private String qrToken; 
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime approvedAt;
    @ManyToOne @JoinColumn(name = "gate_id") private Gate requestedGate;
    public AccessRequest() {}
    public Long getRequestId() { return requestId; } public void setRequestId(Long requestId) { this.requestId = requestId; }
    public User getUser() { return user; } public void setUser(User user) { this.user = user; }
    public RequestStatus getStatus() { return status; } public void setStatus(RequestStatus status) { this.status = status; }
    public AccessLevel getAccessLevel() { return accessLevel; } public void setAccessLevel(AccessLevel accessLevel) { this.accessLevel = accessLevel; }
    public ValidityType getValidityType() { return validityType; } public void setValidityType(ValidityType validityType) { this.validityType = validityType; }
    public String getQrToken() { return qrToken; } public void setQrToken(String qrToken) { this.qrToken = qrToken; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getApprovedAt() { return approvedAt; } public void setApprovedAt(LocalDateTime approvedAt) { this.approvedAt = approvedAt; }
    public Gate getRequestedGate() { return requestedGate; } public void setRequestedGate(Gate requestedGate) { this.requestedGate = requestedGate; }

    public boolean isPassActiveAndApproved() {
        return RequestStatus.APPROVED.equals(this.status) && (this.validUntil == null || this.validUntil.isAfter(java.time.LocalDateTime.now()));
    }


    public boolean isPassActiveAndApproved() {
        return RequestStatus.APPROVED.equals(this.status) && (this.validUntil == null || this.validUntil.isAfter(java.time.LocalDateTime.now()));
    }


    public boolean isPassActiveAndApproved() {
        return RequestStatus.APPROVED.equals(this.status) && (this.validUntil == null || this.validUntil.isAfter(java.time.LocalDateTime.now()));
    }


    public boolean isPassActiveAndApproved() {
        return RequestStatus.APPROVED.equals(this.status) && (this.validUntil == null || this.validUntil.isAfter(java.time.LocalDateTime.now()));
    }


    public boolean isPassActiveAndApproved() {
        return RequestStatus.APPROVED.equals(this.status) && (this.validUntil == null || this.validUntil.isAfter(java.time.LocalDateTime.now()));
    }


    public boolean isPassActiveAndApproved() {
        return RequestStatus.APPROVED.equals(this.status) && (this.validUntil == null || this.validUntil.isAfter(java.time.LocalDateTime.now()));
    }

}
