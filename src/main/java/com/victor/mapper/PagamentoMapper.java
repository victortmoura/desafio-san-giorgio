package com.victor.mapper;

import com.victor.model.dto.request.PagamentoDetalheRequestDTO;
import com.victor.model.dto.response.PagamentoDetalheResponseDTO;
import com.victor.model.dto.response.PagamentoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    @Mapping(target = "statusPagamento", ignore = true)
    PagamentoDetalheResponseDTO toResponseDTO(PagamentoDetalheRequestDTO dto);

    PagamentoResponseDTO toResponseDTO(String codigoVendedor, List<PagamentoDetalheResponseDTO> pagamentos);

}

