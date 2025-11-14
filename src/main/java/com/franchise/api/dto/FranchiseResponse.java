package com.franchise.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para respuesta de Franquicia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranchiseResponse {
    private Long id;
    private String name;
    private List<BranchResponse> branches;
}

