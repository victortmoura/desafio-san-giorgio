package com.victor.model.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoRequestDTO {

    @NotBlank(message = "Código do vendedor é obrigatório.")
    @Schema(description = "Código único do vendedor", example = "VEND123")
    private String codigoVendedor;

    @Valid
    @NotEmpty(message = "A lista de pagamentos não pode estar vazia.")
    @Schema(description = "Lista de pagamentos a serem processados")
    private List<PagamentoDetalheRequestDTO> pagamentos;
}