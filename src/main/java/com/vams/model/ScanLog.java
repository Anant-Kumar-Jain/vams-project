package com.vams.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name = "scan_logs")
public class ScanLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long logId;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false) private User user;
    @Column(nullable = false) private String gateLocation;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private ScanType scanType;
    @Column(nullable = false) private LocalDateTime timestamp = LocalDateTime.now();
    public ScanLog() {}
    public Long getLogId() { return logId; } public void setLogId(Long logId) { this.logId = logId; }
    public User getUser() { return user; } public void setUser(User user) { this.user = user; }
    public String getGateLocation() { return gateLocation; } public void setGateLocation(String gateLocation) { this.gateLocation = gateLocation; }
    public ScanType getScanType() { return scanType; } public void setScanType(ScanType scanType) { this.scanType = scanType; }
    public LocalDateTime getTimestamp() { return timestamp; } public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
