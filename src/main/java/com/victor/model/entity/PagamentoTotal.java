package com.victor.model.entity;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.PagamentoDTO;

public class PagamentoTotal extends Pagamento {

    public PagamentoTotal(String codigoCobranca, Double valorPago) {
        super(codigoCobranca, valorPago, "TOTAL");
    }

    @Override
    public void enviarParaFila(SqsSender sqsSender, PagamentoDTO pagamentoDTO) {
        sqsSender.enviarParaFilaTotal(pagamentoDTO);
    }
}
