package com.franchise.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para crear una nueva Franquicia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFranchiseRequest {
    private String name;
}