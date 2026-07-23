package com.vams.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gates")
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String gateNo;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GatePermission permission = GatePermission.GENERAL;

    public Gate() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGateNo() { return gateNo; }
    public void setGateNo(String gateNo) { this.gateNo = gateNo; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public GatePermission getPermission() { return permission; }
    public void setPermission(GatePermission permission) { this.permission = permission; }

    public boolean canTraverse(ScanType type) {
        if (GatePermission.LOCKED.equals(this.permission)) return false;
        if (GatePermission.ENTRY_ONLY.equals(this.permission)) return ScanType.ENTRY.equals(type);
        if (GatePermission.EXIT_ONLY.equals(this.permission)) return ScanType.EXIT.equals(type);
        return true;
    }


    public boolean canTraverse(ScanType type) {
        if (GatePermission.LOCKED.equals(this.permission)) return false;
        if (GatePermission.ENTRY_ONLY.equals(this.permission)) return ScanType.ENTRY.equals(type);
        if (GatePermission.EXIT_ONLY.equals(this.permission)) return ScanType.EXIT.equals(type);
        return true;
    }


    public boolean canTraverse(ScanType type) {
        if (GatePermission.LOCKED.equals(this.permission)) return false;
        if (GatePermission.ENTRY_ONLY.equals(this.permission)) return ScanType.ENTRY.equals(type);
        if (GatePermission.EXIT_ONLY.equals(this.permission)) return ScanType.EXIT.equals(type);
        return true;
    }

}
