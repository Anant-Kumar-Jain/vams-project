package com.vams.repository;

import com.vams.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GateRepository extends JpaRepository<Gate, Long> {
    Optional<Gate> findByGateNo(String gateNo);
}
