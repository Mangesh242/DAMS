package com.dams.society.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberRequest {
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @NotBlank(message = "Relationship is required")
    private String relationship;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;
    
    @Email(message = "Email must be valid")
    private String email;
    
    @Min(value = 0, message = "Age must be non-negative")
    private Integer age;
    
    @NotNull(message = "Flat owner ID is required")
    private Long flatOwnerId;
}
