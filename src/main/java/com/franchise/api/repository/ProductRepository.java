package com.franchise.api.repository;

import com.franchise.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Producto
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBranchId(Long branchId);

    Optional<Product> findByIdAndBranchId(Long productId, Long branchId);

    @Query("SELECT p FROM Product p WHERE p.branch.id = :branchId ORDER BY p.stock DESC LIMIT 1")
    Optional<Product> findProductWithMaxStockByBranchId(@Param("branchId") Long branchId);
}

