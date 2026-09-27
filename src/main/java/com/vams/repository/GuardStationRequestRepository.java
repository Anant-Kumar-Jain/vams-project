package com.vams.repository;
import com.vams.model.GuardStationRequest;
import com.vams.model.RequestStatus;
import com.vams.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GuardStationRequestRepository extends JpaRepository<GuardStationRequest, Long> {
    List<GuardStationRequest> findByUser(User user);
    List<GuardStationRequest> findByStatus(RequestStatus status);
    Optional<GuardStationRequest> findByUserAndStatus(User user, RequestStatus status);
}
