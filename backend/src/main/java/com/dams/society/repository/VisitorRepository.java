package com.dams.society.repository;

import com.dams.society.entity.Visitor;
import com.dams.society.entity.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    List<Visitor> findByFlatOwnerId(Long flatOwnerId);
    List<Visitor> findByApprovalStatus(ApprovalStatus status);
    List<Visitor> findByFlatOwnerIdAndApprovalStatus(Long flatOwnerId, ApprovalStatus status);
}
