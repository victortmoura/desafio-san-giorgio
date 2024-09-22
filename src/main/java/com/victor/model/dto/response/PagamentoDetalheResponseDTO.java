package com.victor.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDetalheResponseDTO {

    private String codigoCobranca;
    private Double valorPago;

    @Schema(description = "Status do pagamento", allowableValues = {"TOTAL", "PARCIAL", "EXCEDENTE"})
    private String statusPagamento; // Será preenchido após o processamento

}
