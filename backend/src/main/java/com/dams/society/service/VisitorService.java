package com.dams.society.service;

import com.dams.society.dto.VisitorRequest;
import com.dams.society.dto.VisitorResponse;
import com.dams.society.entity.Visitor;
import com.dams.society.entity.FlatOwner;
import com.dams.society.entity.ApprovalStatus;
import com.dams.society.repository.VisitorRepository;
import com.dams.society.repository.FlatOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitorService {
    
    private final VisitorRepository visitorRepository;
    private final FlatOwnerRepository flatOwnerRepository;
    
    @Transactional
    public VisitorResponse createVisitor(VisitorRequest request) {
        FlatOwner flatOwner = flatOwnerRepository.findById(request.getFlatOwnerId())
                .orElseThrow(() -> new RuntimeException("Flat owner not found"));
        
        Visitor visitor = new Visitor();
        visitor.setName(request.getName());
        visitor.setPhoneNumber(request.getPhoneNumber());
        visitor.setPhoto(request.getPhoto());
        visitor.setInTime(request.getInTime());
        visitor.setFlatNumber(request.getFlatNumber());
        visitor.setFlatOwner(flatOwner);
        visitor.setReason(request.getReason());
        visitor.setApprovalStatus(request.getApprovalStatus() != null ? 
                request.getApprovalStatus() : ApprovalStatus.PENDING);
        
        Visitor savedVisitor = visitorRepository.save(visitor);
        return mapToResponse(savedVisitor);
    }
    
    @Transactional(readOnly = true)
    public VisitorResponse getVisitorById(Long id) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));
        return mapToResponse(visitor);
    }
    
    @Transactional(readOnly = true)
    public List<VisitorResponse> getVisitorsByFlatOwnerId(Long flatOwnerId) {
        return visitorRepository.findByFlatOwnerId(flatOwnerId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<VisitorResponse> getPendingVisitorsByFlatOwnerId(Long flatOwnerId) {
        return visitorRepository.findByFlatOwnerIdAndApprovalStatus(
                flatOwnerId, ApprovalStatus.PENDING).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<VisitorResponse> getAllVisitors() {
        return visitorRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public VisitorResponse updateVisitor(Long id, VisitorRequest request) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));
        
        visitor.setName(request.getName());
        visitor.setPhoneNumber(request.getPhoneNumber());
        if (request.getPhoto() != null) {
            visitor.setPhoto(request.getPhoto());
        }
        visitor.setReason(request.getReason());
        
        Visitor updatedVisitor = visitorRepository.save(visitor);
        return mapToResponse(updatedVisitor);
    }
    
    @Transactional
    public VisitorResponse approveVisitor(Long id, String remarks) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));
        
        visitor.setApprovalStatus(ApprovalStatus.APPROVED);
        visitor.setApprovalRemarks(remarks);
        visitor.setApprovalDate(LocalDateTime.now());
        
        Visitor approvedVisitor = visitorRepository.save(visitor);
        return mapToResponse(approvedVisitor);
    }
    
    @Transactional
    public VisitorResponse rejectVisitor(Long id, String remarks) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));
        
        visitor.setApprovalStatus(ApprovalStatus.REJECTED);
        visitor.setApprovalRemarks(remarks);
        visitor.setApprovalDate(LocalDateTime.now());
        
        Visitor rejectedVisitor = visitorRepository.save(visitor);
        return mapToResponse(rejectedVisitor);
    }
    
    @Transactional
    public VisitorResponse setOutTime(Long id, LocalDateTime outTime) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));
        
        visitor.setOutTime(outTime);
        Visitor updatedVisitor = visitorRepository.save(visitor);
        return mapToResponse(updatedVisitor);
    }
    
    @Transactional
    public void deleteVisitor(Long id) {
        if (!visitorRepository.existsById(id)) {
            throw new RuntimeException("Visitor not found");
        }
        visitorRepository.deleteById(id);
    }
    
    private VisitorResponse mapToResponse(Visitor visitor) {
        VisitorResponse response = new VisitorResponse();
        response.setId(visitor.getId());
        response.setName(visitor.getName());
        response.setPhoneNumber(visitor.getPhoneNumber());
        response.setHasPhoto(visitor.getPhoto() != null);
        response.setInTime(visitor.getInTime());
        response.setOutTime(visitor.getOutTime());
        response.setFlatNumber(visitor.getFlatNumber());
        response.setFlatOwnerId(visitor.getFlatOwner().getId());
        response.setReason(visitor.getReason());
        response.setApprovalStatus(visitor.getApprovalStatus());
        response.setApprovalRemarks(visitor.getApprovalRemarks());
        response.setApprovalDate(visitor.getApprovalDate());
        return response;
    }
}
