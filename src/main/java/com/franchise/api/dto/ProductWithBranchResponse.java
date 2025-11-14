package com.franchise.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para mostrar el producto con mayor stock por sucursal
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductWithBranchResponse {
    private Long productId;
    private String productName;
    private Integer stock;
    private Long branchId;
    private String branchName;
}