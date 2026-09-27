package com.vams.service;
import com.vams.model.AccessLevel;
import com.vams.model.AccessRequest;
import com.vams.model.RequestStatus;
import com.vams.model.ValidityType;
import com.vams.repository.AccessRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class ManagerService {
    @Autowired private AccessRequestRepository requestRepository;
    public List<AccessRequest> getPendingRequests() { return requestRepository.findByStatus(RequestStatus.PENDING); }
    public AccessRequest approveRequest(Long requestId, AccessLevel level, ValidityType validity) {
        AccessRequest request = requestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(RequestStatus.APPROVED);
        request.setAccessLevel(level);
        request.setValidityType(validity);
        request.setApprovedAt(LocalDateTime.now());
        request.setQrToken(UUID.randomUUID().toString());
        return requestRepository.save(request);
    }
    public void rejectRequest(Long requestId) {
        AccessRequest request = requestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(RequestStatus.REJECTED);
        requestRepository.save(request);
    }
}
