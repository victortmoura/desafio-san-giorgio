package com.victor.src.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cobrancas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_cobranca", unique = true, nullable = false)
    private String codigoCobranca;

    @Column(name = "valor_original", nullable = false)
    private Double valorOriginal;

}
