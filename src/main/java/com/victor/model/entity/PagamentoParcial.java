package com.victor.model.entity;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.enums.StatusPagamento;

public class PagamentoParcial extends Pagamento {

    public PagamentoParcial(String codigoCobranca, Double valorPago) {
        super(codigoCobranca, valorPago, StatusPagamento.PARCIAL);
    }

    @Override
    public void enviarParaFila(SqsSender sqsSender, PagamentoDetalheRequestDTO pagamentoDetalheRequestDTO) {
        sqsSender.enviarParaFilaParcial(pagamentoDetalheRequestDTO);
    }
}
