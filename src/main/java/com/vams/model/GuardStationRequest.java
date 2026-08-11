package com.vams.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "guard_station_requests")
public class GuardStationRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user; // The Employee (Guard)
    
    @ManyToOne @JoinColumn(name = "gate_id", nullable = false)
    private Gate gate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.PENDING; // PENDING, APPROVED, REJECTED
    
    private LocalDateTime requestedAt = LocalDateTime.now();

    public GuardStationRequest() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Gate getGate() { return gate; }
    public void setGate(Gate gate) { this.gate = gate; }

    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }

    public LocalDateTime getRequestedAt() { return requestedAt; }
    public void setRequestedAt(LocalDateTime requestedAt) { this.requestedAt = requestedAt; }

    public boolean isPendingApproval() {
        return RequestStatus.PENDING.equals(this.stationStatus);
    }


    public boolean isPendingApproval() {
        return RequestStatus.PENDING.equals(this.stationStatus);
    }


    public boolean isPendingApproval() {
        return RequestStatus.PENDING.equals(this.stationStatus);
    }


    public boolean isPendingApproval() {
        return RequestStatus.PENDING.equals(this.stationStatus);
    }


    public boolean isPendingApproval() {
        return RequestStatus.PENDING.equals(this.stationStatus);
    }

}
