package com.dams.society.config;

import com.dams.society.entity.*;
import com.dams.society.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final FlatOwnerRepository flatOwnerRepository;
    private final FamilyMemberRepository familyMemberRepository;
    private final TenantRepository tenantRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final VisitorRepository visitorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create sample flat owners
        FlatOwner flatOwner1 = new FlatOwner();
        flatOwner1.setUsername("john.doe");
        flatOwner1.setPassword(passwordEncoder.encode("password123"));
        flatOwner1.setFirstName("John");
        flatOwner1.setLastName("Doe");
        flatOwner1.setEmail("john.doe@example.com");
        flatOwner1.setPhoneNumber("9876543210");
        flatOwner1.setRole(UserRole.FLAT_OWNER);
        flatOwner1.setFlatNumber("101");
        flatOwner1.setWing("A");
        flatOwner1.setFloor(1);
        flatOwner1.setMaintenanceAmount(5000.0);
        flatOwner1 = flatOwnerRepository.save(flatOwner1);

        FlatOwner flatOwner2 = new FlatOwner();
        flatOwner2.setUsername("jane.smith");
        flatOwner2.setPassword(passwordEncoder.encode("password123"));
        flatOwner2.setFirstName("Jane");
        flatOwner2.setLastName("Smith");
        flatOwner2.setEmail("jane.smith@example.com");
        flatOwner2.setPhoneNumber("9876543211");
        flatOwner2.setRole(UserRole.FLAT_OWNER);
        flatOwner2.setFlatNumber("102");
        flatOwner2.setWing("A");
        flatOwner2.setFloor(1);
        flatOwner2.setMaintenanceAmount(5500.0);
        flatOwner2 = flatOwnerRepository.save(flatOwner2);

        // Create sample family members
        FamilyMember familyMember1 = new FamilyMember();
        familyMember1.setFirstName("Mary");
        familyMember1.setLastName("Doe");
        familyMember1.setRelationship("Spouse");
        familyMember1.setPhoneNumber("9876543212");
        familyMember1.setEmail("mary.doe@example.com");
        familyMember1.setAge(35);
        familyMember1.setFlatOwner(flatOwner1);
        familyMemberRepository.save(familyMember1);

        FamilyMember familyMember2 = new FamilyMember();
        familyMember2.setFirstName("Tom");
        familyMember2.setLastName("Doe");
        familyMember2.setRelationship("Son");
        familyMember2.setPhoneNumber("9876543213");
        familyMember2.setAge(10);
        familyMember2.setFlatOwner(flatOwner1);
        familyMemberRepository.save(familyMember2);

        // Create sample tenant
        Tenant tenant1 = new Tenant();
        tenant1.setFirstName("Bob");
        tenant1.setLastName("Johnson");
        tenant1.setPhoneNumber("9876543214");
        tenant1.setEmail("bob.johnson@example.com");
        tenant1.setLeaseStartDate(LocalDate.now().minusMonths(6));
        tenant1.setLeaseEndDate(LocalDate.now().plusMonths(6));
        tenant1.setRentAmount(15000.0);
        tenant1.setIdProofType("Passport");
        tenant1.setIdProofNumber("AB1234567");
        tenant1.setFlatOwner(flatOwner2);
        tenantRepository.save(tenant1);

        // Create sample maintenance records
        Maintenance maintenance1 = new Maintenance();
        maintenance1.setFlatOwner(flatOwner1);
        maintenance1.setAmount(5000.0);
        maintenance1.setPaymentDate(LocalDate.now().minusMonths(1));
        maintenance1.setPaymentMode("Online");
        maintenance1.setTransactionId("TXN123456");
        maintenance1.setReceiptNumber("REC-2025-ABC123");
        maintenance1.setStatus(PaymentStatus.COMPLETED);
        maintenance1.setRemarks("Monthly maintenance payment");
        maintenanceRepository.save(maintenance1);

        Maintenance maintenance2 = new Maintenance();
        maintenance2.setFlatOwner(flatOwner2);
        maintenance2.setAmount(5500.0);
        maintenance2.setPaymentDate(LocalDate.now());
        maintenance2.setPaymentMode("Cash");
        maintenance2.setReceiptNumber("REC-2025-DEF456");
        maintenance2.setStatus(PaymentStatus.COMPLETED);
        maintenanceRepository.save(maintenance2);

        // Create sample visitors
        Visitor visitor1 = new Visitor();
        visitor1.setName("Alice Brown");
        visitor1.setPhoneNumber("9876543215");
        visitor1.setInTime(LocalDateTime.now().minusHours(2));
        visitor1.setFlatNumber("101");
        visitor1.setFlatOwner(flatOwner1);
        visitor1.setReason("Social visit");
        visitor1.setApprovalStatus(ApprovalStatus.APPROVED);
        visitor1.setApprovalRemarks("Family friend");
        visitor1.setApprovalDate(LocalDateTime.now().minusHours(2));
        visitorRepository.save(visitor1);

        Visitor visitor2 = new Visitor();
        visitor2.setName("Charlie Wilson");
        visitor2.setPhoneNumber("9876543216");
        visitor2.setInTime(LocalDateTime.now());
        visitor2.setFlatNumber("102");
        visitor2.setFlatOwner(flatOwner2);
        visitor2.setReason("Delivery");
        visitor2.setApprovalStatus(ApprovalStatus.PENDING);
        visitorRepository.save(visitor2);

        System.out.println("Sample data initialized successfully!");
    }
}
