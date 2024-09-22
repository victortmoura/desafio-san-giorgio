package com.victor.model.entity;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.enums.StatusPagamento;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pagamento {

    private String codigoCobranca;
    private Double valorPago;
    private StatusPagamento statusPagamento;

    public abstract void enviarParaFila(SqsSender sqsSender, PagamentoDetalheRequestDTO pagamentoDetalheRequestDTO);
}
