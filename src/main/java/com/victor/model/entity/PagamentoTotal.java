package com.victor.model.entity;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.enums.StatusPagamento;

public class PagamentoTotal extends Pagamento {

    public PagamentoTotal(String codigoCobranca, Double valorPago) {
        super(codigoCobranca, valorPago, StatusPagamento.TOTAL);
    }

    @Override
    public void enviarParaFila(SqsSender sqsSender, PagamentoDetalheRequestDTO pagamentoDetalheRequestDTO) {
        sqsSender.enviarParaFilaTotal(pagamentoDetalheRequestDTO);
    }
}
