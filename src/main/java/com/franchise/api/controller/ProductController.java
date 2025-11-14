package com.franchise.api.controller;

import com.franchise.api.dto.CreateProductRequest;
import com.franchise.api.dto.ProductResponse;
import com.franchise.api.dto.UpdateStockRequest;
import com.franchise.api.dto.UpdateProductNameRequest;
import com.franchise.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
public class ProductController {
    private final ProductService productService;

    /**
     * Endpoint para agregar un nuevo producto a una sucursal
     * POST /api/branches/{branchId}/products
     */
    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto en una sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @PathVariable Long branchId,
            @Valid @RequestBody CreateProductRequest request) {
        ProductResponse response = productService.createProduct(branchId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para obtener un producto por ID
     * GET /api/branches/{branchId}/products/{productId}
     */
    @Operation(summary = "Obtener un producto", description = "Obtiene los detalles de un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
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
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto de una sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
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
    @Operation(summary = "Actualizar stock de producto", description = "Modifica el stock de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<ProductResponse> updateProductStock(
            @PathVariable Long branchId,
            @PathVariable Long productId,
            @Valid @RequestBody UpdateStockRequest request) {
        ProductResponse response = productService.updateProductStock(productId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para actualizar el nombre de un producto
     * PATCH /api/branches/{branchId}/products/{productId}/name
     */
    @Operation(summary = "Actualizar nombre de producto", description = "Actualiza el nombre de un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PatchMapping("/{productId}/name")
    public ResponseEntity<ProductResponse> updateProductName(
            @PathVariable Long branchId,
            @PathVariable Long productId,
            @Valid @RequestBody UpdateProductNameRequest request) {
        ProductResponse response = productService.updateProductName(branchId, productId, request);
        return ResponseEntity.ok(response);
    }
}
