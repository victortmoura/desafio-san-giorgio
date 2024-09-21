package com.victor.model.entity;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.PagamentoDTO;

public class PagamentoParcial extends Pagamento {

    public PagamentoParcial(String codigoCobranca, Double valorPago) {
        super(codigoCobranca, valorPago, "PARCIAL");
    }

    @Override
    public void enviarParaFila(SqsSender sqsSender, PagamentoDTO pagamentoDTO) {
        sqsSender.enviarParaFilaParcial(pagamentoDTO);
    }
}
