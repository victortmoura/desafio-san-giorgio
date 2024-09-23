package com.victor.processor;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoProcessor {

    @Autowired
    private SqsSender sqsSender;

    public Pagamento definirPagamentoComBaseNoValor(PagamentoDetalheRequestDTO pagamentoDetalheRequestDTO, Cobranca cobranca) {

        Double valorOriginal = cobranca.getValorOriginal();
        Double valorPago = pagamentoDetalheRequestDTO.getValorPago();
        String codigoCobranca = pagamentoDetalheRequestDTO.getCodigoCobranca();

        Pagamento pagamentoProcessado;

        if (valorPago < valorOriginal) {
            pagamentoProcessado = new PagamentoParcial(codigoCobranca, valorPago);
        } else if (valorPago.equals(valorOriginal)) {
            pagamentoProcessado = new PagamentoTotal(codigoCobranca, valorPago);
        } else {
            pagamentoProcessado = new PagamentoExcedente(codigoCobranca, valorPago);
        }

        pagamentoProcessado.enviarParaFila(sqsSender, pagamentoDetalheRequestDTO);

        return pagamentoProcessado;
    }

}
