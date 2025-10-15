package com.dams.society.controller;

import com.dams.society.dto.FamilyMemberRequest;
import com.dams.society.dto.FamilyMemberResponse;
import com.dams.society.service.FamilyMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/family-members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FamilyMemberController {
    
    private final FamilyMemberService familyMemberService;
    
    @PostMapping
    public ResponseEntity<FamilyMemberResponse> createFamilyMember(@Valid @RequestBody FamilyMemberRequest request) {
        FamilyMemberResponse response = familyMemberService.createFamilyMember(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FamilyMemberResponse> getFamilyMemberById(@PathVariable Long id) {
        FamilyMemberResponse response = familyMemberService.getFamilyMemberById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/flat-owner/{flatOwnerId}")
    public ResponseEntity<List<FamilyMemberResponse>> getFamilyMembersByFlatOwnerId(@PathVariable Long flatOwnerId) {
        List<FamilyMemberResponse> responses = familyMemberService.getFamilyMembersByFlatOwnerId(flatOwnerId);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping
    public ResponseEntity<List<FamilyMemberResponse>> getAllFamilyMembers() {
        List<FamilyMemberResponse> responses = familyMemberService.getAllFamilyMembers();
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FamilyMemberResponse> updateFamilyMember(
            @PathVariable Long id,
            @Valid @RequestBody FamilyMemberRequest request) {
        FamilyMemberResponse response = familyMemberService.updateFamilyMember(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFamilyMember(@PathVariable Long id) {
        familyMemberService.deleteFamilyMember(id);
        return ResponseEntity.noContent().build();
    }
}
