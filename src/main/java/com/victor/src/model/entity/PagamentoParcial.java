package com.victor.src.model.entity;

import com.victor.src.messaging.SqsSender;
import com.victor.src.model.dto.PagamentoDTO;

public class PagamentoParcial extends Pagamento {

    public PagamentoParcial(String codigoCobranca, Double valorPago) {
        super(codigoCobranca, valorPago, "PARCIAL");
    }

    @Override
    public void enviarParaFila(SqsSender sqsSender, PagamentoDTO pagamentoDTO) {
        sqsSender.enviarParaFilaParcial(pagamentoDTO);
    }
}
