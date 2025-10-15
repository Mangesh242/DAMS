package com.dams.society.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantRequest {
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    
    @NotNull(message = "Lease start date is required")
    private LocalDate leaseStartDate;
    
    @NotNull(message = "Lease end date is required")
    private LocalDate leaseEndDate;
    
    @NotNull(message = "Rent amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Rent amount must be positive")
    private Double rentAmount;
    
    @NotBlank(message = "ID proof type is required")
    private String idProofType;
    
    @NotBlank(message = "ID proof number is required")
    private String idProofNumber;
    
    @NotNull(message = "Flat owner ID is required")
    private Long flatOwnerId;
}
