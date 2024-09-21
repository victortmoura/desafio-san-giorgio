package com.victor.repository;

import java.util.Optional;

import com.victor.model.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Optional<Vendedor> findByCodigoVendedor(String codigoVendedor);
}