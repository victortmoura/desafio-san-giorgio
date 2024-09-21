package com.victor.src.repository;

import java.util.Optional;

import com.victor.src.model.entity.Cobranca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CobrancaRepository extends JpaRepository<Cobranca, Long> {
    Optional<Cobranca> findByCodigoCobranca(String codigoCobranca);
}