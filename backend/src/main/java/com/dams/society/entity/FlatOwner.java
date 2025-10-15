package com.dams.society.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flat_owners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FlatOwner extends User {
    
    @Column(nullable = false, unique = true)
    private String flatNumber;
    
    @Column(nullable = false)
    private String wing;
    
    @Column(nullable = false)
    private Integer floor;
    
    @Column(nullable = false)
    private Double maintenanceAmount;
    
    @OneToMany(mappedBy = "flatOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FamilyMember> familyMembers = new ArrayList<>();
    
    @OneToMany(mappedBy = "flatOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tenant> tenants = new ArrayList<>();
    
    @OneToMany(mappedBy = "flatOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Maintenance> maintenanceRecords = new ArrayList<>();
}
