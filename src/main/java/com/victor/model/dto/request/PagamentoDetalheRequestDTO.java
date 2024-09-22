package com.victor.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDetalheRequestDTO {

    @NotBlank(message = "Código da cobrança é obrigatório.")
    @Schema(description = "Código único da cobrança", example = "Cobranca1")
    private String codigoCobranca;

    @NotNull(message = "Valor do pagamento é obrigatório.")
    @Positive(message = "O valor do pagamento deve ser positivo.")
    @Schema(description = "Valor pago pelo cliente", example = "100.0")
    private Double valorPago;

}
