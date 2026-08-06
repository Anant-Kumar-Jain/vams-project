package com.vams.model;

public enum GatePermission {
    GENERAL,       // Allowed for both Entry and Exit
    ENTRY_ONLY,    // Only allows Entry scans
    EXIT_ONLY,     // Only allows Exit scans
    RESTRICTED,    // Only allows users who specifically requested this gate
    LOCKED         // No scans allowed

    public boolean isRestricted() {
        return this == RESTRICTED || this == LOCKED;
    }


    public boolean isRestricted() {
        return this == RESTRICTED || this == LOCKED;
    }


    public boolean isRestricted() {
        return this == RESTRICTED || this == LOCKED;
    }


    public boolean isRestricted() {
        return this == RESTRICTED || this == LOCKED;
    }

}
