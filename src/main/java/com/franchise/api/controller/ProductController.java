package com.franchise.api.controller;

import com.franchise.api.dto.CreateProductRequest;
import com.franchise.api.dto.ProductResponse;
import com.franchise.api.dto.UpdateStockRequest;
import com.franchise.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para operaciones de Producto
 */
@RestController
@RequestMapping("/branches/{branchId}/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * Endpoint para agregar un nuevo producto a una sucursal
     * POST /api/branches/{branchId}/products
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @PathVariable Long branchId,
            @RequestBody CreateProductRequest request) {
        ProductResponse response = productService.createProduct(branchId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para obtener un producto por ID
     * GET /api/branches/{branchId}/products/{productId}
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long branchId,
            @PathVariable Long productId) {
        ProductResponse response = productService.getProductById(productId);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para eliminar un producto de una sucursal
     * DELETE /api/branches/{branchId}/products/{productId}
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long branchId,
            @PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para modificar el stock de un producto
     * PATCH /api/branches/{branchId}/products/{productId}/stock
     */
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<ProductResponse> updateProductStock(
            @PathVariable Long branchId,
            @PathVariable Long productId,
            @RequestBody UpdateStockRequest request) {
        ProductResponse response = productService.updateProductStock(productId, request);
        return ResponseEntity.ok(response);
    }
}
