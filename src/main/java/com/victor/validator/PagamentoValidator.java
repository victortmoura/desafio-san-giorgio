package com.victor.validator;

import com.victor.exception.CobrancaNotFoundException;
import com.victor.model.entity.Cobranca;
import com.victor.repository.CobrancaRepository;
import com.victor.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagamentoValidator {

    @Autowired
    private final VendedorRepository vendedorRepository;

    @Autowired
    private final CobrancaRepository cobrancaRepository;

    public boolean isVendedorNotExist(String codigoVendedor) {
        return !vendedorRepository.existsByCodigoVendedor(codigoVendedor);
    }

    public Cobranca buscarCobranca(String codigoCobranca) {
        return cobrancaRepository.findByCodigoCobranca(codigoCobranca)
                .orElseThrow(() -> new CobrancaNotFoundException("Código da cobrança não encontrado: " + codigoCobranca));
    }
}
