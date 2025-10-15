package com.dams.society.repository;

import com.dams.society.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findByFlatOwnerId(Long flatOwnerId);
    List<Tenant> findByActive(boolean active);
}
