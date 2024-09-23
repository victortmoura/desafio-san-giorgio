package com.victor.service;

import com.victor.exception.VendedorNotFoundException;
import com.victor.mapper.PagamentoMapper;
import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.dto.request.PagamentoRequestDTO;
import com.victor.model.dto.response.PagamentoDetalheResponseDTO;
import com.victor.model.dto.response.PagamentoResponseDTO;
import com.victor.model.entity.Cobranca;
import com.victor.model.entity.Pagamento;
import com.victor.model.entity.PagamentoTotal;
import com.victor.model.enums.StatusPagamento;
import com.victor.processor.PagamentoProcessor;
import com.victor.validator.PagamentoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @Mock
    private PagamentoValidator pagamentoValidator;

    @Mock
    private PagamentoProcessor pagamentoProcessor;

    @Mock
    private PagamentoMapper pagamentoMapper;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    @DisplayName("Deve lançar VendedorNotFoundException quando o vendedor não existir")
    void shouldThrowVendedorNotFoundExceptionWhenVendedorDoesNotExist() {
        PagamentoRequestDTO requestDTO = new PagamentoRequestDTO();
        requestDTO.setCodigoVendedor("VEND123");

        when(pagamentoValidator.isVendedorNotExist(anyString())).thenReturn(true);

        assertThatThrownBy(() -> pagamentoService.processarPagamentos(requestDTO))
                .isInstanceOf(VendedorNotFoundException.class)
                .hasMessage("Vendedor não encontrado.");
    }

    @Test
    @DisplayName("Deve processar pagamentos corretamente")
    void shouldProcessPaymentsCorrectly() {
        PagamentoRequestDTO requestDTO = new PagamentoRequestDTO();
        requestDTO.setCodigoVendedor("VEND123");

        PagamentoDetalheRequestDTO detalheRequestDTO = new PagamentoDetalheRequestDTO();
        detalheRequestDTO.setCodigoCobranca("COBRANCA123");
        detalheRequestDTO.setValorPago(10d);

        requestDTO.setPagamentos(List.of(detalheRequestDTO));

        Cobranca cobranca = new Cobranca();

        PagamentoDetalheResponseDTO detalheResponseDTO = new PagamentoDetalheResponseDTO();
        detalheResponseDTO.setCodigoCobranca("COBRANCA123");
        detalheResponseDTO.setValorPago(10d);

        PagamentoResponseDTO pagamentoResponseDTO = new PagamentoResponseDTO();
        pagamentoResponseDTO.setCodigoVendedor("VEND123");
        pagamentoResponseDTO.setPagamentos(List.of(detalheResponseDTO));

        PagamentoTotal pagamentoTotal = new PagamentoTotal("COBRANCA123", 10d);

        when(pagamentoValidator.isVendedorNotExist(anyString())).thenReturn(false);
        when(pagamentoValidator.buscarCobranca(anyString())).thenReturn(cobranca);
        when(pagamentoProcessor.definirPagamentoComBaseNoValor(detalheRequestDTO, cobranca)).thenReturn(pagamentoTotal);
        when(pagamentoMapper.toResponseDTO(any())).thenReturn(detalheResponseDTO);
        when(pagamentoMapper.toResponseDTO(anyString(), any())).thenReturn(pagamentoResponseDTO);

        PagamentoResponseDTO resposta = pagamentoService.processarPagamentos(requestDTO);

        assertThat(resposta).isNotNull();
        assertThat(resposta.getPagamentos()).contains(detalheResponseDTO);
        assertThat(resposta.getPagamentos().get(0).getStatusPagamento()).isEqualTo(StatusPagamento.TOTAL.toString());
        verify(pagamentoValidator).isVendedorNotExist(anyString());
        verify(pagamentoValidator).buscarCobranca(anyString());
        verify(pagamentoProcessor).definirPagamentoComBaseNoValor(any(), any());
        verify(pagamentoMapper).toResponseDTO(any());
        verify(pagamentoMapper).toResponseDTO(anyString(), any());
    }
}
