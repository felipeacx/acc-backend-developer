package com.franchise.api.controller;

import com.franchise.api.dto.ProductWithBranchResponse;
import com.franchise.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para reportes y consultas especiales
 */
@RestController
@RequestMapping("/api/franchises/{franchiseId}/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ProductService productService;

    /**
     * Endpoint para obtener el producto con mayor stock por sucursal de una franquicia
     * GET /api/franchises/{franchiseId}/reports/max-stock-products
     */
    @GetMapping("/max-stock-products")
    public ResponseEntity<List<ProductWithBranchResponse>> getMaxStockProductsByFranchise(
            @PathVariable Long franchiseId) {
        List<ProductWithBranchResponse> response = productService.getProductsWithMaxStockByFranchise(franchiseId);
        return ResponseEntity.ok(response);
    }
}

