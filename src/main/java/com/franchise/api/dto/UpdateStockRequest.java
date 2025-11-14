package com.franchise.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para modificar Stock de un producto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStockRequest {
    private Integer stock;
}