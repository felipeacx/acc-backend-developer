package com.franchise.api.repository;

import com.franchise.api.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio de la entidad Franquicia
 */
@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
    Optional<Franchise> findByName(String name);
}