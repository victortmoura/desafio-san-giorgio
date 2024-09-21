package com.victor.mapper;

import com.victor.model.dto.PagamentoDTO;
import com.victor.model.entity.Cobranca;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);

    // Se precisar mapear atributos específicos
    // Exemplo:
    // @Mapping(source = "codigoCobranca", target = "codigoCobranca")
    PagamentoDTO cobrancaToPagamentoDTO(Cobranca cobranca);

    // Outros mapeamentos conforme necessário
}

