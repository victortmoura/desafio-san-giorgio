package com.victor.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_vendedor", unique = true, nullable = false)
    private String codigoVendedor;

    @Column(name = "nome", nullable = false)
    private String nome;

    // Outros atributos relevantes
}
