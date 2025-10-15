package com.dams.society.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String phoneNumber;
    
    @Lob
    private byte[] photo;
    
    @Column(nullable = false)
    private LocalDateTime inTime;
    
    private LocalDateTime outTime;
    
    @Column(nullable = false)
    private String flatNumber;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flat_owner_id", nullable = false)
    private FlatOwner flatOwner;
    
    @Column(nullable = false)
    private String reason;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    
    private String approvalRemarks;
    
    private LocalDateTime approvalDate;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
