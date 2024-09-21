package com.victor.src.model.entity;

import com.victor.src.messaging.SqsSender;
import com.victor.src.model.dto.PagamentoDTO;

public class PagamentoTotal extends Pagamento {

    public PagamentoTotal(String codigoCobranca, Double valorPago) {
        super(codigoCobranca, valorPago, "TOTAL");
    }

    @Override
    public void enviarParaFila(SqsSender sqsSender, PagamentoDTO pagamentoDTO) {
        sqsSender.enviarParaFilaTotal(pagamentoDTO);
    }
}
