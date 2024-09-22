package com.victor.service;

import com.victor.exception.CobrancaNotFoundException;
import com.victor.exception.VendedorNotFoundException;
import com.victor.mapper.PagamentoMapper;
import com.victor.messaging.SqsSender;
import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.dto.request.PagamentoRequestDTO;
import com.victor.model.dto.response.PagamentoDetalheResponseDTO;
import com.victor.model.dto.response.PagamentoResponseDTO;
import com.victor.model.entity.*;
import com.victor.repository.CobrancaRepository;
import com.victor.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private CobrancaRepository cobrancaRepository;

    @Autowired
    private SqsSender sqsSender;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Transactional
    public PagamentoResponseDTO processarPagamentos(PagamentoRequestDTO dto) {

        verificarSeVendedorExiste(dto);

        List<PagamentoDetalheResponseDTO> pagamentosResposta = new ArrayList<>();

        for (PagamentoDetalheRequestDTO requestDTO : dto.getPagamentos()) {

            Cobranca cobranca = buscarCobranca(requestDTO);

            Pagamento pagamentoProcessado = definirPagamentoComBaseNoValor(requestDTO, cobranca);
            pagamentoProcessado.enviarParaFila(sqsSender, requestDTO);

            PagamentoDetalheResponseDTO pagamentoResposta = pagamentoMapper.toResponseDTO(requestDTO);
            pagamentoResposta.setStatusPagamento(pagamentoProcessado.getStatusPagamento().toString());

            pagamentosResposta.add(pagamentoResposta);
        }

        PagamentoResponseDTO responseDTO = pagamentoMapper.toResponseDTO(dto.getCodigoVendedor(), pagamentosResposta);

        return responseDTO;
    }

    private Cobranca buscarCobranca(PagamentoDetalheRequestDTO pagamento) {
        return cobrancaRepository.findByCodigoCobranca(pagamento.getCodigoCobranca())
                .orElseThrow(() -> new CobrancaNotFoundException("Código da cobrança não encontrado: " + pagamento.getCodigoCobranca()));
    }

    private Vendedor verificarSeVendedorExiste(PagamentoRequestDTO dto) {
        return vendedorRepository.findByCodigoVendedor(dto.getCodigoVendedor())
                .orElseThrow(() -> new VendedorNotFoundException("Vendedor não encontrado."));
    }

    private static Pagamento definirPagamentoComBaseNoValor(PagamentoDetalheRequestDTO pagamento, Cobranca cobranca) {

        Double valorOriginal = cobranca.getValorOriginal();
        Double valorPago = pagamento.getValorPago();

        Pagamento pagamentoProcessado;

        if (valorPago < valorOriginal) {
            pagamentoProcessado = new PagamentoParcial(pagamento.getCodigoCobranca(), valorPago);
        } else if (valorPago.equals(valorOriginal)) {
            pagamentoProcessado = new PagamentoTotal(pagamento.getCodigoCobranca(), valorPago);
        } else {
            pagamentoProcessado = new PagamentoExcedente(pagamento.getCodigoCobranca(), valorPago);
        }

        return pagamentoProcessado;
    }

}
