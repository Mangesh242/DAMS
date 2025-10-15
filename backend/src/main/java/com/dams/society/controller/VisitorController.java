package com.dams.society.controller;

import com.dams.society.dto.VisitorRequest;
import com.dams.society.dto.VisitorResponse;
import com.dams.society.service.VisitorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visitors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VisitorController {
    
    private final VisitorService visitorService;
    
    @PostMapping
    public ResponseEntity<VisitorResponse> createVisitor(@Valid @RequestBody VisitorRequest request) {
        VisitorResponse response = visitorService.createVisitor(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VisitorResponse> getVisitorById(@PathVariable Long id) {
        VisitorResponse response = visitorService.getVisitorById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/flat-owner/{flatOwnerId}")
    public ResponseEntity<List<VisitorResponse>> getVisitorsByFlatOwnerId(@PathVariable Long flatOwnerId) {
        List<VisitorResponse> responses = visitorService.getVisitorsByFlatOwnerId(flatOwnerId);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/flat-owner/{flatOwnerId}/pending")
    public ResponseEntity<List<VisitorResponse>> getPendingVisitorsByFlatOwnerId(@PathVariable Long flatOwnerId) {
        List<VisitorResponse> responses = visitorService.getPendingVisitorsByFlatOwnerId(flatOwnerId);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping
    public ResponseEntity<List<VisitorResponse>> getAllVisitors() {
        List<VisitorResponse> responses = visitorService.getAllVisitors();
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VisitorResponse> updateVisitor(
            @PathVariable Long id,
            @Valid @RequestBody VisitorRequest request) {
        VisitorResponse response = visitorService.updateVisitor(id, request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/approve")
    public ResponseEntity<VisitorResponse> approveVisitor(
            @PathVariable Long id,
            @RequestBody(required = false) Map<String, String> body) {
        String remarks = body != null ? body.get("remarks") : null;
        VisitorResponse response = visitorService.approveVisitor(id, remarks);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/reject")
    public ResponseEntity<VisitorResponse> rejectVisitor(
            @PathVariable Long id,
            @RequestBody(required = false) Map<String, String> body) {
        String remarks = body != null ? body.get("remarks") : null;
        VisitorResponse response = visitorService.rejectVisitor(id, remarks);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/out")
    public ResponseEntity<VisitorResponse> setOutTime(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        LocalDateTime outTime = body.get("outTime") != null ? 
                LocalDateTime.parse(body.get("outTime")) : LocalDateTime.now();
        VisitorResponse response = visitorService.setOutTime(id, outTime);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable Long id) {
        visitorService.deleteVisitor(id);
        return ResponseEntity.noContent().build();
    }
}
