package com.victor.model.entity;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.enums.StatusPagamento;
import com.victor.processor.PagamentoStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PagamentoExcedente extends Pagamento implements PagamentoStrategy {

    @Value("${sqs.queue.excedente}")
    private String filaExcedente;

    @Override
    public Pagamento processarPagamento(PagamentoDetalheRequestDTO pagamentoDetalheRequestDTO) {
        this.setCodigoCobranca(pagamentoDetalheRequestDTO.getCodigoCobranca());
        this.setValorPago(pagamentoDetalheRequestDTO.getValorPago());
        return this;
    }

    @Override
    public boolean isAplicavel(Double valorPago, Double valorOriginal) {
        return valorPago > valorOriginal;
    }

    @Override
    public StatusPagamento getStatusPagamento() {
        return StatusPagamento.EXCEDENTE;
    }

    @Override
    public void enviarParaFila(SqsSender sqsSender, PagamentoDetalheRequestDTO pagamentoDetalheRequestDTO) {
        sqsSender.enviarParaFila(filaExcedente, pagamentoDetalheRequestDTO);
    }

}
