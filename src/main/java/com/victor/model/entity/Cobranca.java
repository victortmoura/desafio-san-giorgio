package com.victor.model.entity;

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
    private String codigoCobranca;

    @Column(name = "valor_original", nullable = false)
    private Double valorOriginal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_vendedor", nullable = false)
    private Vendedor vendedor;

}
