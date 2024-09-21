package com.victor.service;

import com.victor.exception.CobrancaNotFoundException;
import com.victor.exception.VendedorNotFoundException;
import com.victor.messaging.SqsSender;
import com.victor.model.dto.PagamentoDTO;
import com.victor.model.dto.ProcessamentoPagamentoDTO;
import com.victor.model.entity.*;
import com.victor.src.model.entity.*;
import com.victor.repository.CobrancaRepository;
import com.victor.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private CobrancaRepository cobrancaRepository;

    @Autowired
    private SqsSender sqsSender;

    @Transactional
    public ProcessamentoPagamentoDTO processarPagamentos(ProcessamentoPagamentoDTO dto) {

        // Validar existência do vendedor
        Vendedor vendedor = vendedorRepository.findByCodigoVendedor(dto.getCodigoVendedor())
                .orElseThrow(() -> new VendedorNotFoundException("Vendedor não encontrado."));

        // Processar cada pagamento
        for (PagamentoDTO pagamento : dto.getPagamentos()) {

            // Validar existência da cobrança
            Cobranca cobranca = cobrancaRepository.findByCodigoCobranca(pagamento.getCodigoCobranca())
                    .orElseThrow(() -> new CobrancaNotFoundException("Código da cobrança não encontrado: " + pagamento.getCodigoCobranca()));

            // Determinar status do pagamento
            Double valorOriginal = cobranca.getValorOriginal();
            Double valorPago = pagamento.getValorPago();

            Pagamento pagamentoProcessado;

            if (valorPago < valorOriginal) {
                pagamentoProcessado = new PagamentoParcial(pagamento.getCodigoCobranca(), valorPago);
            } else if (valorPago.equals(valorOriginal)) {
                pagamentoProcessado = new PagamentoTotal(pagamento.getCodigoCobranca(), valorPago);
            } else {
                pagamentoProcessado = new PagamentoExcedente(pagamento.getCodigoCobranca(), valorPago);
            }

            pagamentoProcessado.enviarParaFila(sqsSender, pagamento);

            pagamento.setStatusPagamento(pagamentoProcessado.getStatusPagamento());

        }

        return dto;
    }
}
