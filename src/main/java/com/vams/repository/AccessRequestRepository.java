package com.vams.repository;

import com.vams.model.AccessRequest;
import com.vams.model.RequestStatus;
import com.vams.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long> {
    List<AccessRequest> findByStatus(RequestStatus status);
    Optional<AccessRequest> findByQrToken(String qrToken);
    List<AccessRequest> findByUser(User user);
}
