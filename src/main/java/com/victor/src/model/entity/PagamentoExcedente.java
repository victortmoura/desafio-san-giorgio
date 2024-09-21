package com.victor.src.model.entity;

import com.victor.src.messaging.SqsSender;
import com.victor.src.model.dto.PagamentoDTO;

public class PagamentoExcedente extends Pagamento {

    public PagamentoExcedente(String codigoCobranca, Double valorPago) {
        super(codigoCobranca, valorPago, "EXCEDENTE");
    }

    @Override
    public void enviarParaFila(SqsSender sqsSender, PagamentoDTO pagamentoDTO) {
        sqsSender.enviarParaFilaExcedente(pagamentoDTO);
    }
}
