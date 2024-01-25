package com.formalizacaodigital.contratacaocartaodecredito.controller.converter;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.SimulacaoNegociacaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.domain.SimulacaoNegociacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SimulacaoNegociacaoToSimulacaoNegociacaoResourceConverter {

    private final ModelMapper mapper;

    public SimulacaoNegociacaoResource convert(SimulacaoNegociacao simulacaoNegociacao){
        return mapper.map(simulacaoNegociacao, SimulacaoNegociacaoResource.class);
    }
}
