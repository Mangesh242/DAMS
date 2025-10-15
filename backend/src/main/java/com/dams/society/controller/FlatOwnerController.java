package com.dams.society.controller;

import com.dams.society.dto.FlatOwnerRequest;
import com.dams.society.dto.FlatOwnerResponse;
import com.dams.society.service.FlatOwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/flat-owners")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FlatOwnerController {
    
    private final FlatOwnerService flatOwnerService;
    
    @PostMapping
    public ResponseEntity<FlatOwnerResponse> createFlatOwner(@Valid @RequestBody FlatOwnerRequest request) {
        FlatOwnerResponse response = flatOwnerService.createFlatOwner(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FlatOwnerResponse> getFlatOwnerById(@PathVariable Long id) {
        FlatOwnerResponse response = flatOwnerService.getFlatOwnerById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<FlatOwnerResponse>> getAllFlatOwners() {
        List<FlatOwnerResponse> responses = flatOwnerService.getAllFlatOwners();
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FlatOwnerResponse> updateFlatOwner(
            @PathVariable Long id,
            @Valid @RequestBody FlatOwnerRequest request) {
        FlatOwnerResponse response = flatOwnerService.updateFlatOwner(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlatOwner(@PathVariable Long id) {
        flatOwnerService.deleteFlatOwner(id);
        return ResponseEntity.noContent().build();
    }
}
