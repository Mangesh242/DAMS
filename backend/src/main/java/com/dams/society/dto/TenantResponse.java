package com.dams.society.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
    private Double rentAmount;
    private String idProofType;
    private String idProofNumber;
    private Long flatOwnerId;
    private String flatNumber;
    private boolean active;
}
