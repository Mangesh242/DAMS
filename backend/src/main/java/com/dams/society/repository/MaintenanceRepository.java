package com.dams.society.repository;

import com.dams.society.entity.Maintenance;
import com.dams.society.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByFlatOwnerId(Long flatOwnerId);
    List<Maintenance> findByStatus(PaymentStatus status);
}
