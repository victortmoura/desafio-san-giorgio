package com.victor.service;

import com.victor.exception.VendedorNotFoundException;
import com.victor.mapper.PagamentoMapper;
import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.dto.request.PagamentoRequestDTO;
import com.victor.model.dto.response.PagamentoDetalheResponseDTO;
import com.victor.model.dto.response.PagamentoResponseDTO;
import com.victor.model.entity.*;
import com.victor.processor.PagamentoProcessor;
import com.victor.validator.PagamentoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoValidator pagamentoValidator;

    @Autowired
    private PagamentoProcessor pagamentoProcessor;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Transactional
    public PagamentoResponseDTO processarPagamentos(PagamentoRequestDTO dto) {

        if (pagamentoValidator.isVendedorNotExist(dto.getCodigoVendedor())) {
            throw new VendedorNotFoundException("Vendedor não encontrado.");
        }

        List<PagamentoDetalheResponseDTO> pagamentosResposta = new ArrayList<>();

        for (PagamentoDetalheRequestDTO pagamentoDetalheRequestDTO : dto.getPagamentos()) {

            Cobranca cobranca = pagamentoValidator.buscarCobranca(pagamentoDetalheRequestDTO.getCodigoCobranca());

            Pagamento pagamentoProcessado = pagamentoProcessor.definirPagamentoComBaseNoValor(pagamentoDetalheRequestDTO, cobranca);

            PagamentoDetalheResponseDTO pagamentoResposta = pagamentoMapper.toResponseDTO(pagamentoDetalheRequestDTO);
            pagamentoResposta.setStatusPagamento(pagamentoProcessado.getStatusPagamento().toString());

            pagamentosResposta.add(pagamentoResposta);
        }

        return pagamentoMapper.toResponseDTO(dto.getCodigoVendedor(), pagamentosResposta);
    }

}
