package com.franchise.api.service;

import com.franchise.api.entity.Branch;
import com.franchise.api.entity.Product;
import com.franchise.api.dto.CreateProductRequest;
import com.franchise.api.dto.ProductResponse;
import com.franchise.api.dto.ProductWithBranchResponse;
import com.franchise.api.dto.UpdateStockRequest;
import com.franchise.api.repository.BranchRepository;
import com.franchise.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para manejar operaciones de Producto
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    /**
     * Crea un nuevo producto en una sucursal
     */
    public ProductResponse createProduct(Long branchId, CreateProductRequest request) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada: " + branchId));

        Product product = Product.builder()
                .name(request.getName())
                .stock(request.getStock())
                .branch(branch)
                .build();
        product = productRepository.save(product);
        return mapToResponse(product);
    }

    /**
     * Obtiene un producto por ID
     */
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productId));
        return mapToResponse(product);
    }

    /**
     * Obtiene un producto por ID y sucursal ID
     */
    public Product getProductByIdAndBranchId(Long productId, Long branchId) {
        return productRepository.findByIdAndBranchId(productId, branchId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en la sucursal especificada"));
    }

    /**
     * Elimina un producto
     */
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productId));
        productRepository.delete(product);
    }

    /**
     * Modifica el stock de un producto
     */
    public ProductResponse updateProductStock(Long productId, UpdateStockRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productId));
        product.setStock(request.getStock());
        product = productRepository.save(product);
        return mapToResponse(product);
    }

    /**
     * Obtiene los productos con mayor stock por sucursal de una franquicia
     */
    public List<ProductWithBranchResponse> getProductsWithMaxStockByFranchise(Long franchiseId) {
        List<Branch> branches = branchRepository.findByFranchiseId(franchiseId);

        return branches.stream()
                .flatMap(branch -> productRepository.findProductWithMaxStockByBranchId(branch.getId())
                        .stream()
                        .map(product -> ProductWithBranchResponse.builder()
                                .productId(product.getId())
                                .productName(product.getName())
                                .stock(product.getStock())
                                .branchId(branch.getId())
                                .branchName(branch.getName())
                                .build()))
                .collect(Collectors.toList());
    }

    /**
     * Mapea una entidad Product a ProductResponse
     */
    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .branchId(product.getBranch().getId())
                .build();
    }
}
