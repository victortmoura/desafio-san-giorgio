package com.victor.processor;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.entity.Cobranca;
import com.victor.model.entity.Pagamento;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PagamentoProcessor {

    private List<PagamentoStrategy> strategies;
    private SqsSender sqsSender;

    public Pagamento definirPagamentoComBaseNoValor(PagamentoDetalheRequestDTO pagamentoDetalheRequestDTO, Cobranca cobranca) {

        Double valorOriginal = cobranca.getValorOriginal();
        Double valorPago = pagamentoDetalheRequestDTO.getValorPago();

        PagamentoStrategy pagamentoStrategy = strategies.stream()
                .filter(strategy -> strategy.isAplicavel(valorPago, valorOriginal))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estratégia de pagamento não definida."));

        Pagamento pagamento = pagamentoStrategy.processarPagamento(pagamentoDetalheRequestDTO);

        pagamentoStrategy.enviarParaFila(sqsSender, pagamentoDetalheRequestDTO);

        return pagamento;

    }

}
