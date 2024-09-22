package com.victor.controller;

import com.victor.model.dto.request.PagamentoRequestDTO;
import com.victor.model.dto.response.PagamentoResponseDTO;
import com.victor.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagamentos")
@Tag(name = "Pagamentos", description = "Operações relacionadas a pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @Operation(summary = "Processa pagamentos para um vendedor")
    public ResponseEntity<PagamentoResponseDTO> processarPagamentos(@Valid @RequestBody PagamentoRequestDTO dto) {
        PagamentoResponseDTO resultado = pagamentoService.processarPagamentos(dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
