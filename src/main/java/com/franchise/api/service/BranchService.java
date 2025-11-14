package com.franchise.api.service;

import com.franchise.api.entity.Branch;
import com.franchise.api.entity.Franchise;
import com.franchise.api.dto.BranchResponse;
import com.franchise.api.dto.CreateBranchRequest;
import com.franchise.api.dto.ProductResponse;
import com.franchise.api.dto.UpdateBranchNameRequest;
import com.franchise.api.repository.BranchRepository;
import com.franchise.api.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Servicio para manejar operaciones de Sucursal
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BranchService {
    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    /**
     * Crea una nueva sucursal en una franquicia
     */
    public BranchResponse createBranch(Long franchiseId, CreateBranchRequest request) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new NoSuchElementException("Franquicia no encontrada: " + franchiseId));

        Branch branch = Branch.builder()
                .name(request.getName())
                .franchise(franchise)
                .build();
        branch = branchRepository.save(branch);
        return mapToResponse(branch);
    }

    /**
     * Obtiene una sucursal por ID
     */
    public BranchResponse getBranchById(Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NoSuchElementException("Sucursal no encontrada: " + branchId));
        return mapToResponse(branch);
    }

    /**
     * Obtiene una sucursal por ID y franquicia ID
     */
    public Branch getBranchByIdAndFranchiseId(Long branchId, Long franchiseId) {
        return branchRepository.findByIdAndFranchiseId(branchId, franchiseId)
                .orElseThrow(() -> new NoSuchElementException("Sucursal no encontrada en la franquicia especificada"));
    }

    /**
     * Actualiza el nombre de una sucursal
     */
    public BranchResponse updateBranchName(Long franchiseId, Long branchId, UpdateBranchNameRequest request) {
        Branch branch = branchRepository.findByIdAndFranchiseId(branchId, franchiseId)
                .orElseThrow(() -> new NoSuchElementException("Sucursal no encontrada en la franquicia especificada"));

        branch.setName(request.getName());
        branch = branchRepository.save(branch);

        return mapToResponse(branch);
    }

    /**
     * Mapea una entidad Branch a BranchResponse
     */
    public BranchResponse mapToResponse(Branch branch) {
        return BranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .franchiseId(branch.getFranchise().getId())
                .products(branch.getProducts() != null ?
                        branch.getProducts().stream()
                                .map(product -> ProductResponse.builder()
                                        .id(product.getId())
                                        .name(product.getName())
                                        .stock(product.getStock())
                                        .branchId(product.getBranch().getId())
                                        .build())
                                .collect(Collectors.toList())
                        : null)
                .build();
    }
}

