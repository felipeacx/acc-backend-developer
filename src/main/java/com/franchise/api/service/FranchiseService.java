package com.franchise.api.service;

import com.franchise.api.entity.Franchise;
import com.franchise.api.dto.CreateFranchiseRequest;
import com.franchise.api.dto.FranchiseResponse;
import com.franchise.api.dto.UpdateFranchiseNameRequest;
import com.franchise.api.dto.BranchResponse;
import com.franchise.api.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Servicio para manejar operaciones de Franquicia
 */
@Service
@RequiredArgsConstructor
@Transactional
public class FranchiseService {
    private final FranchiseRepository franchiseRepository;
    private final BranchService branchService;

    /**
     * Crea una nueva franquicia
     */
    public FranchiseResponse createFranchise(CreateFranchiseRequest request) {
        Franchise franchise = Franchise.builder()
                .name(request.getName())
                .build();
        franchise = franchiseRepository.save(franchise);
        return mapToResponse(franchise);
    }

    /**
     * Obtiene una franquicia por ID
     */
    public FranchiseResponse getFranchiseById(Long id) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada: " + id));
        return mapToResponse(franchise);
    }

    /**
     * Actualiza el nombre de una franquicia
     */
    public FranchiseResponse updateFranchiseName(Long id, UpdateFranchiseNameRequest request) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada: " + id));

        franchise.setName(request.getName());
        try {
            franchise = franchiseRepository.save(franchise);
            franchiseRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("El nombre ya existe en el sistema. Por favor, utiliza un nombre único.", e);
        }

        return mapToResponse(franchise);
    }

    /**
     * Mapea una entidad Franchise a FranchiseResponse
     */
    private FranchiseResponse mapToResponse(Franchise franchise) {
        List<BranchResponse> branches = null;
        try {
            if (franchise.getBranches() != null && !franchise.getBranches().isEmpty()) {
                branches = franchise.getBranches().stream()
                        .map(branchService::mapToResponse)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            // Las branches podrían no estar inicializadas si la sesión se cerró
            branches = null;
        }

        return FranchiseResponse.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .branches(branches)
                .build();
    }
}
