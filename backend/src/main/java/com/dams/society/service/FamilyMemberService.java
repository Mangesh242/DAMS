package com.dams.society.service;

import com.dams.society.dto.FamilyMemberRequest;
import com.dams.society.dto.FamilyMemberResponse;
import com.dams.society.entity.FamilyMember;
import com.dams.society.entity.FlatOwner;
import com.dams.society.repository.FamilyMemberRepository;
import com.dams.society.repository.FlatOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamilyMemberService {
    
    private final FamilyMemberRepository familyMemberRepository;
    private final FlatOwnerRepository flatOwnerRepository;
    
    @Transactional
    public FamilyMemberResponse createFamilyMember(FamilyMemberRequest request) {
        FlatOwner flatOwner = flatOwnerRepository.findById(request.getFlatOwnerId())
                .orElseThrow(() -> new RuntimeException("Flat owner not found"));
        
        FamilyMember familyMember = new FamilyMember();
        familyMember.setFirstName(request.getFirstName());
        familyMember.setLastName(request.getLastName());
        familyMember.setRelationship(request.getRelationship());
        familyMember.setPhoneNumber(request.getPhoneNumber());
        familyMember.setEmail(request.getEmail());
        familyMember.setAge(request.getAge());
        familyMember.setFlatOwner(flatOwner);
        
        FamilyMember savedMember = familyMemberRepository.save(familyMember);
        return mapToResponse(savedMember);
    }
    
    @Transactional(readOnly = true)
    public FamilyMemberResponse getFamilyMemberById(Long id) {
        FamilyMember familyMember = familyMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Family member not found"));
        return mapToResponse(familyMember);
    }
    
    @Transactional(readOnly = true)
    public List<FamilyMemberResponse> getFamilyMembersByFlatOwnerId(Long flatOwnerId) {
        return familyMemberRepository.findByFlatOwnerId(flatOwnerId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<FamilyMemberResponse> getAllFamilyMembers() {
        return familyMemberRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public FamilyMemberResponse updateFamilyMember(Long id, FamilyMemberRequest request) {
        FamilyMember familyMember = familyMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Family member not found"));
        
        familyMember.setFirstName(request.getFirstName());
        familyMember.setLastName(request.getLastName());
        familyMember.setRelationship(request.getRelationship());
        familyMember.setPhoneNumber(request.getPhoneNumber());
        familyMember.setEmail(request.getEmail());
        familyMember.setAge(request.getAge());
        
        FamilyMember updatedMember = familyMemberRepository.save(familyMember);
        return mapToResponse(updatedMember);
    }
    
    @Transactional
    public void deleteFamilyMember(Long id) {
        if (!familyMemberRepository.existsById(id)) {
            throw new RuntimeException("Family member not found");
        }
        familyMemberRepository.deleteById(id);
    }
    
    private FamilyMemberResponse mapToResponse(FamilyMember familyMember) {
        FamilyMemberResponse response = new FamilyMemberResponse();
        response.setId(familyMember.getId());
        response.setFirstName(familyMember.getFirstName());
        response.setLastName(familyMember.getLastName());
        response.setRelationship(familyMember.getRelationship());
        response.setPhoneNumber(familyMember.getPhoneNumber());
        response.setEmail(familyMember.getEmail());
        response.setAge(familyMember.getAge());
        response.setFlatOwnerId(familyMember.getFlatOwner().getId());
        response.setFlatNumber(familyMember.getFlatOwner().getFlatNumber());
        return response;
    }
}
