package com.dams.society.service;

import com.dams.society.dto.TenantRequest;
import com.dams.society.dto.TenantResponse;
import com.dams.society.entity.Tenant;
import com.dams.society.entity.FlatOwner;
import com.dams.society.repository.TenantRepository;
import com.dams.society.repository.FlatOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TenantService {
    
    private final TenantRepository tenantRepository;
    private final FlatOwnerRepository flatOwnerRepository;
    
    @Transactional
    public TenantResponse createTenant(TenantRequest request) {
        FlatOwner flatOwner = flatOwnerRepository.findById(request.getFlatOwnerId())
                .orElseThrow(() -> new RuntimeException("Flat owner not found"));
        
        Tenant tenant = new Tenant();
        tenant.setFirstName(request.getFirstName());
        tenant.setLastName(request.getLastName());
        tenant.setPhoneNumber(request.getPhoneNumber());
        tenant.setEmail(request.getEmail());
        tenant.setLeaseStartDate(request.getLeaseStartDate());
        tenant.setLeaseEndDate(request.getLeaseEndDate());
        tenant.setRentAmount(request.getRentAmount());
        tenant.setIdProofType(request.getIdProofType());
        tenant.setIdProofNumber(request.getIdProofNumber());
        tenant.setFlatOwner(flatOwner);
        
        Tenant savedTenant = tenantRepository.save(tenant);
        return mapToResponse(savedTenant);
    }
    
    @Transactional(readOnly = true)
    public TenantResponse getTenantById(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
        return mapToResponse(tenant);
    }
    
    @Transactional(readOnly = true)
    public List<TenantResponse> getTenantsByFlatOwnerId(Long flatOwnerId) {
        return tenantRepository.findByFlatOwnerId(flatOwnerId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<TenantResponse> getAllTenants() {
        return tenantRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public TenantResponse updateTenant(Long id, TenantRequest request) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
        
        tenant.setFirstName(request.getFirstName());
        tenant.setLastName(request.getLastName());
        tenant.setPhoneNumber(request.getPhoneNumber());
        tenant.setEmail(request.getEmail());
        tenant.setLeaseStartDate(request.getLeaseStartDate());
        tenant.setLeaseEndDate(request.getLeaseEndDate());
        tenant.setRentAmount(request.getRentAmount());
        tenant.setIdProofType(request.getIdProofType());
        tenant.setIdProofNumber(request.getIdProofNumber());
        
        Tenant updatedTenant = tenantRepository.save(tenant);
        return mapToResponse(updatedTenant);
    }
    
    @Transactional
    public void deleteTenant(Long id) {
        if (!tenantRepository.existsById(id)) {
            throw new RuntimeException("Tenant not found");
        }
        tenantRepository.deleteById(id);
    }
    
    private TenantResponse mapToResponse(Tenant tenant) {
        TenantResponse response = new TenantResponse();
        response.setId(tenant.getId());
        response.setFirstName(tenant.getFirstName());
        response.setLastName(tenant.getLastName());
        response.setPhoneNumber(tenant.getPhoneNumber());
        response.setEmail(tenant.getEmail());
        response.setLeaseStartDate(tenant.getLeaseStartDate());
        response.setLeaseEndDate(tenant.getLeaseEndDate());
        response.setRentAmount(tenant.getRentAmount());
        response.setIdProofType(tenant.getIdProofType());
        response.setIdProofNumber(tenant.getIdProofNumber());
        response.setFlatOwnerId(tenant.getFlatOwner().getId());
        response.setFlatNumber(tenant.getFlatOwner().getFlatNumber());
        response.setActive(tenant.isActive());
        return response;
    }
}
