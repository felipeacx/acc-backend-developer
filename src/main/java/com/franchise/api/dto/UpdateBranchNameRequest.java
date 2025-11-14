package com.franchise.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para actualizar el nombre de una Sucursal
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBranchNameRequest {
    @NotBlank(message = "El nombre de la sucursal no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;
}

