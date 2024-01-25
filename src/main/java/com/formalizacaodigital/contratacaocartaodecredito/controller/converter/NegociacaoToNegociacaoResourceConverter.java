package com.formalizacaodigital.contratacaocartaodecredito.controller.converter;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.NegociacaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NegociacaoToNegociacaoResourceConverter {

    private final ModelMapper mapper;

    public NegociacaoResource convert(Negociacao negociacao){
        return mapper.map(negociacao, NegociacaoResource.class);
    }
}
