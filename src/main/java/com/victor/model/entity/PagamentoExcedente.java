package com.victor.model.entity;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.enums.StatusPagamento;

public class PagamentoExcedente extends Pagamento {

    public PagamentoExcedente(String codigoCobranca, Double valorPago) {
        super(codigoCobranca, valorPago, StatusPagamento.EXCEDENTE);
    }

    @Override
    public void enviarParaFila(SqsSender sqsSender, PagamentoDetalheRequestDTO pagamentoDetalheRequestDTO) {
        sqsSender.enviarParaFilaExcedente(pagamentoDetalheRequestDTO);
    }
}
