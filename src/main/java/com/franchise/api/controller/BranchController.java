package com.franchise.api.controller;

import com.franchise.api.dto.CreateBranchRequest;
import com.franchise.api.dto.BranchResponse;
import com.franchise.api.service.BranchService;
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
public class BranchController {
    private final BranchService branchService;

    /**
     * Endpoint para agregar una nueva sucursal a una franquicia
     * POST /api/franchises/{franchiseId}/branches
     */
    @PostMapping
    public ResponseEntity<BranchResponse> createBranch(
            @PathVariable Long franchiseId,
            @RequestBody CreateBranchRequest request) {
        BranchResponse response = branchService.createBranch(franchiseId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para obtener una sucursal por ID
     * GET /api/franchises/{franchiseId}/branches/{branchId}
     */
    @GetMapping("/{branchId}")
    public ResponseEntity<BranchResponse> getBranchById(
            @PathVariable Long franchiseId,
            @PathVariable Long branchId) {
        BranchResponse response = branchService.getBranchById(branchId);
        return ResponseEntity.ok(response);
    }
}