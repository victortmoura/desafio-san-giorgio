package com.victor.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoResponseDTO {

    private String codigoVendedor;
    private List<PagamentoDetalheResponseDTO> pagamentos;

}
