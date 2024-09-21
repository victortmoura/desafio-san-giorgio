package com.victor.model.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessamentoPagamentoDTO {

    @NotBlank(message = "Código do vendedor é obrigatório.")
    private String codigoVendedor;

    @NotEmpty(message = "A lista de pagamentos não pode estar vazia.")
    @Valid
    private List<PagamentoDTO> pagamentos;
}