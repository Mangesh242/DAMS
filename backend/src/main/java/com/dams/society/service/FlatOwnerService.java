package com.dams.society.service;

import com.dams.society.dto.FlatOwnerRequest;
import com.dams.society.dto.FlatOwnerResponse;
import com.dams.society.entity.FlatOwner;
import com.dams.society.entity.UserRole;
import com.dams.society.repository.FlatOwnerRepository;
import com.dams.society.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlatOwnerService {
    
    private final FlatOwnerRepository flatOwnerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public FlatOwnerResponse createFlatOwner(FlatOwnerRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (flatOwnerRepository.existsByFlatNumber(request.getFlatNumber())) {
            throw new RuntimeException("Flat number already exists");
        }
        
        FlatOwner flatOwner = new FlatOwner();
        flatOwner.setUsername(request.getUsername());
        flatOwner.setPassword(passwordEncoder.encode(request.getPassword()));
        flatOwner.setFirstName(request.getFirstName());
        flatOwner.setLastName(request.getLastName());
        flatOwner.setEmail(request.getEmail());
        flatOwner.setPhoneNumber(request.getPhoneNumber());
        flatOwner.setRole(UserRole.FLAT_OWNER);
        flatOwner.setFlatNumber(request.getFlatNumber());
        flatOwner.setWing(request.getWing());
        flatOwner.setFloor(request.getFloor());
        flatOwner.setMaintenanceAmount(request.getMaintenanceAmount());
        
        FlatOwner savedFlatOwner = flatOwnerRepository.save(flatOwner);
        return mapToResponse(savedFlatOwner);
    }
    
    @Transactional(readOnly = true)
    public FlatOwnerResponse getFlatOwnerById(Long id) {
        FlatOwner flatOwner = flatOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flat owner not found"));
        return mapToResponse(flatOwner);
    }
    
    @Transactional(readOnly = true)
    public List<FlatOwnerResponse> getAllFlatOwners() {
        return flatOwnerRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public FlatOwnerResponse updateFlatOwner(Long id, FlatOwnerRequest request) {
        FlatOwner flatOwner = flatOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flat owner not found"));
        
        if (!flatOwner.getEmail().equals(request.getEmail()) && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        flatOwner.setFirstName(request.getFirstName());
        flatOwner.setLastName(request.getLastName());
        flatOwner.setEmail(request.getEmail());
        flatOwner.setPhoneNumber(request.getPhoneNumber());
        flatOwner.setWing(request.getWing());
        flatOwner.setFloor(request.getFloor());
        flatOwner.setMaintenanceAmount(request.getMaintenanceAmount());
        
        FlatOwner updatedFlatOwner = flatOwnerRepository.save(flatOwner);
        return mapToResponse(updatedFlatOwner);
    }
    
    @Transactional
    public void deleteFlatOwner(Long id) {
        if (!flatOwnerRepository.existsById(id)) {
            throw new RuntimeException("Flat owner not found");
        }
        flatOwnerRepository.deleteById(id);
    }
    
    private FlatOwnerResponse mapToResponse(FlatOwner flatOwner) {
        FlatOwnerResponse response = new FlatOwnerResponse();
        response.setId(flatOwner.getId());
        response.setUsername(flatOwner.getUsername());
        response.setFirstName(flatOwner.getFirstName());
        response.setLastName(flatOwner.getLastName());
        response.setEmail(flatOwner.getEmail());
        response.setPhoneNumber(flatOwner.getPhoneNumber());
        response.setFlatNumber(flatOwner.getFlatNumber());
        response.setWing(flatOwner.getWing());
        response.setFloor(flatOwner.getFloor());
        response.setMaintenanceAmount(flatOwner.getMaintenanceAmount());
        response.setActive(flatOwner.isActive());
        return response;
    }
}
