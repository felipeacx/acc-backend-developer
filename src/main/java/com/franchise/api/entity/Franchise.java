package com.franchise.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad que representa una Franquicia
 */
@Entity
@Table(name = "franchises")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Branch> branches;
}
