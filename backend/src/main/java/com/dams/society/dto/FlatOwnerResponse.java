package com.dams.society.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlatOwnerResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String flatNumber;
    private String wing;
    private Integer floor;
    private Double maintenanceAmount;
    private boolean active;
}
