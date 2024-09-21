package com.victor.model.entity;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.PagamentoDTO;

public class PagamentoExcedente extends Pagamento {

    public PagamentoExcedente(String codigoCobranca, Double valorPago) {
        super(codigoCobranca, valorPago, "EXCEDENTE");
    }

    @Override
    public void enviarParaFila(SqsSender sqsSender, PagamentoDTO pagamentoDTO) {
        sqsSender.enviarParaFilaExcedente(pagamentoDTO);
    }
}
