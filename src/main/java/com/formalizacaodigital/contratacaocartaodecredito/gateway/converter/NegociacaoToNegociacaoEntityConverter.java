package com.formalizacaodigital.contratacaocartaodecredito.gateway.converter;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.NegociacaoEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NegociacaoToNegociacaoEntityConverter {

    private final ModelMapper mapper;

    public NegociacaoEntity convert(Negociacao negociacao){
        return mapper.map(negociacao, NegociacaoEntity.class);
    }
}
