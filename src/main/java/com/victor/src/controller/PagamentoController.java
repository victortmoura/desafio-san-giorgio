package com.victor.src.controller;

import com.victor.src.model.dto.ProcessamentoPagamentoDTO;
import com.victor.src.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<ProcessamentoPagamentoDTO> processarPagamentos(@Valid @RequestBody ProcessamentoPagamentoDTO dto) {
        ProcessamentoPagamentoDTO resultado = pagamentoService.processarPagamentos(dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
