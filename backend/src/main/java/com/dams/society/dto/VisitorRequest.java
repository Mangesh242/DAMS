package com.dams.society.dto;

import com.dams.society.entity.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorRequest {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;
    
    private byte[] photo;
    
    @NotNull(message = "In time is required")
    private LocalDateTime inTime;
    
    @NotBlank(message = "Flat number is required")
    private String flatNumber;
    
    @NotNull(message = "Flat owner ID is required")
    private Long flatOwnerId;
    
    @NotBlank(message = "Reason is required")
    private String reason;
    
    private ApprovalStatus approvalStatus;
}
