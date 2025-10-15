package com.dams.society.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlatOwnerRequest {
    
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;
    
    @NotBlank(message = "Flat number is required")
    private String flatNumber;
    
    @NotBlank(message = "Wing is required")
    private String wing;
    
    @NotNull(message = "Floor is required")
    @Min(value = 0, message = "Floor must be non-negative")
    private Integer floor;
    
    @NotNull(message = "Maintenance amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Maintenance amount must be positive")
    private Double maintenanceAmount;
}
