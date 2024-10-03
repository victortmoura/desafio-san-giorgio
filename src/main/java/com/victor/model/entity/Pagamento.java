package com.victor.model.entity;

import com.victor.model.enums.StatusPagamento;
import lombok.Data;

@Data
public abstract class Pagamento {

    private String codigoCobranca;
    private Double valorPago;

    public abstract StatusPagamento getStatusPagamento();

}
