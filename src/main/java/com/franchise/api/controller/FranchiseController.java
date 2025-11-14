package com.franchise.api.controller;

import com.franchise.api.dto.CreateFranchiseRequest;
import com.franchise.api.dto.FranchiseResponse;
import com.franchise.api.dto.UpdateFranchiseNameRequest;
import com.franchise.api.service.FranchiseService;
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
 * Controlador REST para operaciones de Franquicia
 */
@RestController
@RequestMapping("/franchises")
@RequiredArgsConstructor
@Tag(name = "Franquicias", description = "Operaciones relacionadas con franquicias")
public class FranchiseController {
    private final FranchiseService franchiseService;

    /**
     * Endpoint para agregar una nueva franquicia
     * POST /api/franchises
     */
    @Operation(summary = "Crear una nueva franquicia", description = "Crea una nueva franquicia en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Franquicia creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "El nombre de la franquicia ya existe")
    })
    @PostMapping
    public ResponseEntity<FranchiseResponse> createFranchise(@Valid @RequestBody CreateFranchiseRequest request) {
        FranchiseResponse response = franchiseService.createFranchise(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para obtener una franquicia por ID
     * GET /api/franchises/{id}
     */
    @Operation(summary = "Obtener una franquicia", description = "Obtiene los detalles de una franquicia por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Franquicia encontrada"),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FranchiseResponse> getFranchiseById(@PathVariable Long id) {
        FranchiseResponse response = franchiseService.getFranchiseById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para actualizar el nombre de una franquicia
     * PATCH /api/franchises/{id}/name
     */
    @Operation(summary = "Actualizar nombre de franquicia", description = "Actualiza el nombre de una franquicia existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Franquicia no encontrada"),
            @ApiResponse(responseCode = "409", description = "El nombre ya existe")
    })
    @PatchMapping("/{id}/name")
    public ResponseEntity<FranchiseResponse> updateFranchiseName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFranchiseNameRequest request) {
        FranchiseResponse response = franchiseService.updateFranchiseName(id, request);
        return ResponseEntity.ok(response);
    }
}