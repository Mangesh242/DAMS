package com.dams.society.controller;

import com.dams.society.dto.MaintenanceRequest;
import com.dams.society.dto.MaintenanceResponse;
import com.dams.society.service.MaintenanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MaintenanceController {
    
    private final MaintenanceService maintenanceService;
    
    @PostMapping
    public ResponseEntity<MaintenanceResponse> createMaintenance(@Valid @RequestBody MaintenanceRequest request) {
        MaintenanceResponse response = maintenanceService.createMaintenance(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> getMaintenanceById(@PathVariable Long id) {
        MaintenanceResponse response = maintenanceService.getMaintenanceById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/flat-owner/{flatOwnerId}")
    public ResponseEntity<List<MaintenanceResponse>> getMaintenanceByFlatOwnerId(@PathVariable Long flatOwnerId) {
        List<MaintenanceResponse> responses = maintenanceService.getMaintenanceByFlatOwnerId(flatOwnerId);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping
    public ResponseEntity<List<MaintenanceResponse>> getAllMaintenance() {
        List<MaintenanceResponse> responses = maintenanceService.getAllMaintenance();
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> updateMaintenance(
            @PathVariable Long id,
            @Valid @RequestBody MaintenanceRequest request) {
        MaintenanceResponse response = maintenanceService.updateMaintenance(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
