package com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.SimulacaoNegociacaoDTO;

public class SimulacaoNegociacaoDTOFactory {

    public static SimulacaoNegociacaoDTO criaSimulacaoNegociacaoDTO(Long clienteId){
        return new SimulacaoNegociacaoDTO(clienteId);
    }
}
