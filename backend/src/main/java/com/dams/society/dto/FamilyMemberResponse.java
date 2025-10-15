package com.dams.society.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String relationship;
    private String phoneNumber;
    private String email;
    private Integer age;
    private Long flatOwnerId;
    private String flatNumber;
}
