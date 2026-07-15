package com.vams.repository;

import com.vams.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GateRepository extends JpaRepository<Gate, Long> {
    Optional<Gate> findByGateNo(String gateNo);

    @org.springframework.data.jpa.repository.Query("SELECT g FROM Gate g WHERE g.active = true ORDER BY g.gateNo ASC")
    java.util.List<Gate> findActiveGatesOrdered();


    @org.springframework.data.jpa.repository.Query("SELECT g FROM Gate g WHERE g.active = true ORDER BY g.gateNo ASC")
    java.util.List<Gate> findActiveGatesOrdered();

}
