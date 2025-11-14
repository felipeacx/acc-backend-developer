package com.franchise.api.service;

import com.franchise.api.entity.Franchise;
import com.franchise.api.dto.CreateFranchiseRequest;
import com.franchise.api.dto.FranchiseResponse;
import com.franchise.api.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada: " + id));
        return mapToResponse(franchise);
    }

    /**
     * Mapea una entidad Franchise a FranchiseResponse
     */
    private FranchiseResponse mapToResponse(Franchise franchise) {
        return FranchiseResponse.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .branches(franchise.getBranches() != null ?
                        franchise.getBranches().stream()
                                .map(branchService::mapToResponse)
                                .collect(Collectors.toList())
                        : null)
                .build();
    }
}
