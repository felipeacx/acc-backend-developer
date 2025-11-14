package com.franchise.api.controller;

import com.franchise.api.dto.CreateBranchRequest;
import com.franchise.api.dto.BranchResponse;
import com.franchise.api.dto.UpdateBranchNameRequest;
import com.franchise.api.service.BranchService;
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
 * Controlador REST para operaciones de Sucursal
 */
@RestController
@RequestMapping("/franchises/{franchiseId}/branches")
@RequiredArgsConstructor
@Tag(name = "Sucursales", description = "Operaciones relacionadas con sucursales")
public class BranchController {
    private final BranchService branchService;

    /**
     * Endpoint para agregar una nueva sucursal a una franquicia
     * POST /api/franchises/{franchiseId}/branches
     */
    @Operation(summary = "Crear una nueva sucursal", description = "Crea una nueva sucursal en una franquicia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @PostMapping
    public ResponseEntity<BranchResponse> createBranch(
            @PathVariable Long franchiseId,
            @Valid @RequestBody CreateBranchRequest request) {
        BranchResponse response = branchService.createBranch(franchiseId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para obtener una sucursal por ID
     * GET /api/franchises/{franchiseId}/branches/{branchId}
     */
    @Operation(summary = "Obtener una sucursal", description = "Obtiene los detalles de una sucursal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/{branchId}")
    public ResponseEntity<BranchResponse> getBranchById(
            @PathVariable Long franchiseId,
            @PathVariable Long branchId) {
        BranchResponse response = branchService.getBranchById(branchId);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para actualizar el nombre de una sucursal
     * PATCH /api/franchises/{franchiseId}/branches/{branchId}/name
     */
    @Operation(summary = "Actualizar nombre de sucursal", description = "Actualiza el nombre de una sucursal existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PatchMapping("/{branchId}/name")
    public ResponseEntity<BranchResponse> updateBranchName(
            @PathVariable Long franchiseId,
            @PathVariable Long branchId,
            @Valid @RequestBody UpdateBranchNameRequest request) {
        BranchResponse response = branchService.updateBranchName(franchiseId, branchId, request);
        return ResponseEntity.ok(response);
    }
}