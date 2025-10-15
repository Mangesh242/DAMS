package com.dams.society.repository;

import com.dams.society.entity.FlatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FlatOwnerRepository extends JpaRepository<FlatOwner, Long> {
    Optional<FlatOwner> findByFlatNumber(String flatNumber);
    boolean existsByFlatNumber(String flatNumber);
}
