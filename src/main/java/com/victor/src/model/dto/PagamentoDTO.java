package com.victor.src.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {

    @NotBlank(message = "Código da cobrança é obrigatório.")
    private String codigoCobranca;

    @NotNull(message = "Valor do pagamento é obrigatório.")
    @Positive(message = "O valor do pagamento deve ser positivo.")
    private Double valorPago;

    private String statusPagamento; // Será preenchido após o processamento
}
