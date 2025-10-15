package com.dams.society.service;

import com.dams.society.dto.MaintenanceRequest;
import com.dams.society.dto.MaintenanceResponse;
import com.dams.society.entity.Maintenance;
import com.dams.society.entity.FlatOwner;
import com.dams.society.entity.PaymentStatus;
import com.dams.society.repository.MaintenanceRepository;
import com.dams.society.repository.FlatOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    
    private final MaintenanceRepository maintenanceRepository;
    private final FlatOwnerRepository flatOwnerRepository;
    
    @Transactional
    public MaintenanceResponse createMaintenance(MaintenanceRequest request) {
        FlatOwner flatOwner = flatOwnerRepository.findById(request.getFlatOwnerId())
                .orElseThrow(() -> new RuntimeException("Flat owner not found"));
        
        Maintenance maintenance = new Maintenance();
        maintenance.setFlatOwner(flatOwner);
        maintenance.setAmount(request.getAmount());
        maintenance.setPaymentDate(request.getPaymentDate());
        maintenance.setPaymentMode(request.getPaymentMode());
        maintenance.setTransactionId(request.getTransactionId());
        maintenance.setReceiptNumber(generateReceiptNumber());
        maintenance.setStatus(request.getStatus());
        maintenance.setRemarks(request.getRemarks());
        
        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);
        return mapToResponse(savedMaintenance);
    }
    
    @Transactional(readOnly = true)
    public MaintenanceResponse getMaintenanceById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance record not found"));
        return mapToResponse(maintenance);
    }
    
    @Transactional(readOnly = true)
    public List<MaintenanceResponse> getMaintenanceByFlatOwnerId(Long flatOwnerId) {
        return maintenanceRepository.findByFlatOwnerId(flatOwnerId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<MaintenanceResponse> getAllMaintenance() {
        return maintenanceRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public MaintenanceResponse updateMaintenance(Long id, MaintenanceRequest request) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance record not found"));
        
        maintenance.setAmount(request.getAmount());
        maintenance.setPaymentDate(request.getPaymentDate());
        maintenance.setPaymentMode(request.getPaymentMode());
        maintenance.setTransactionId(request.getTransactionId());
        maintenance.setStatus(request.getStatus());
        maintenance.setRemarks(request.getRemarks());
        
        Maintenance updatedMaintenance = maintenanceRepository.save(maintenance);
        return mapToResponse(updatedMaintenance);
    }
    
    @Transactional
    public void deleteMaintenance(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new RuntimeException("Maintenance record not found");
        }
        maintenanceRepository.deleteById(id);
    }
    
    private String generateReceiptNumber() {
        return "REC-" + LocalDate.now().getYear() + "-" + 
               UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private MaintenanceResponse mapToResponse(Maintenance maintenance) {
        MaintenanceResponse response = new MaintenanceResponse();
        response.setId(maintenance.getId());
        response.setFlatOwnerId(maintenance.getFlatOwner().getId());
        response.setFlatNumber(maintenance.getFlatOwner().getFlatNumber());
        response.setAmount(maintenance.getAmount());
        response.setPaymentDate(maintenance.getPaymentDate());
        response.setPaymentMode(maintenance.getPaymentMode());
        response.setTransactionId(maintenance.getTransactionId());
        response.setReceiptNumber(maintenance.getReceiptNumber());
        response.setStatus(maintenance.getStatus());
        response.setRemarks(maintenance.getRemarks());
        return response;
    }
}
