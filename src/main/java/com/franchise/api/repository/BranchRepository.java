package com.franchise.api.repository;

import com.franchise.api.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de la entidad Sucursal
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByFranchiseId(Long franchiseId);
    Optional<Branch> findByIdAndFranchiseId(Long branchId, Long franchiseId);
}

