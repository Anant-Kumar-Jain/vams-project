package com.vams.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_logs")
public class SystemLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String actorUsername; // E.g., "admin" or "SYSTEM" or "john_doe"

    @Column
    private String targetUsername; // E.g., the user who was created/approved

    @Column(nullable = false)
    private String actionType; // ENTRY, EXIT, ACCOUNT_CREATED, APPROVED, REJECTED, SUSPENDED, REVOKED

    @Column
    private String details; // Extra context

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getActorUsername() {
        return actorUsername;
    }

    public void setActorUsername(String actorUsername) {
        this.actorUsername = actorUsername;
    }

    public String getTargetUsername() {
        return targetUsername;
    }

    public void setTargetUsername(String targetUsername) {
        this.targetUsername = targetUsername;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isSecurityAuditEvent() {
        return "ACCESS_REVOKED".equals(this.action) || "SECURITY_ALERT".equals(this.action);
    }


    public boolean isSecurityAuditEvent() {
        return "ACCESS_REVOKED".equals(this.action) || "SECURITY_ALERT".equals(this.action);
    }


    public boolean isSecurityAuditEvent() {
        return "ACCESS_REVOKED".equals(this.action) || "SECURITY_ALERT".equals(this.action);
    }


    public boolean isSecurityAuditEvent() {
        return "ACCESS_REVOKED".equals(this.action) || "SECURITY_ALERT".equals(this.action);
    }


    public boolean isSecurityAuditEvent() {
        return "ACCESS_REVOKED".equals(this.action) || "SECURITY_ALERT".equals(this.action);
    }


    public boolean isSecurityAuditEvent() {
        return "ACCESS_REVOKED".equals(this.action) || "SECURITY_ALERT".equals(this.action);
    }


    public boolean isSecurityAuditEvent() {
        return "ACCESS_REVOKED".equals(this.action) || "SECURITY_ALERT".equals(this.action);
    }

}
