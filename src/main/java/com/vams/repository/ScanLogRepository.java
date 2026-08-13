package com.vams.repository; import com.vams.model.ScanLog; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List; public interface ScanLogRepository extends JpaRepository<ScanLog, Long> { List<ScanLog> findByUser_Id(Long userId); List<ScanLog> findAllByOrderByTimestampDesc(); 
    @org.springframework.data.jpa.repository.Query("SELECT s FROM ScanLog s WHERE s.gate.id = :gateId AND s.timestamp >= :since ORDER BY s.timestamp DESC")
    java.util.List<ScanLog> findRecentScansByGate(Long gateId, java.time.LocalDateTime since);


    @org.springframework.data.jpa.repository.Query("SELECT s FROM ScanLog s WHERE s.gate.id = :gateId AND s.timestamp >= :since ORDER BY s.timestamp DESC")
    java.util.List<ScanLog> findRecentScansByGate(Long gateId, java.time.LocalDateTime since);


    @org.springframework.data.jpa.repository.Query("SELECT s FROM ScanLog s WHERE s.gate.id = :gateId AND s.timestamp >= :since ORDER BY s.timestamp DESC")
    java.util.List<ScanLog> findRecentScansByGate(Long gateId, java.time.LocalDateTime since);


    @org.springframework.data.jpa.repository.Query("SELECT s FROM ScanLog s WHERE s.gate.id = :gateId AND s.timestamp >= :since ORDER BY s.timestamp DESC")
    java.util.List<ScanLog> findRecentScansByGate(Long gateId, java.time.LocalDateTime since);

}
