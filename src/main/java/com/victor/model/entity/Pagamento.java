package com.victor.model.entity;

import com.victor.messaging.SqsSender;
import com.victor.model.dto.PagamentoDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pagamento {

    private String codigoCobranca;
    private Double valorPago;
    private String statusPagamento;

    public abstract void enviarParaFila(SqsSender sqsSender, PagamentoDTO pagamentoDTO);
}
