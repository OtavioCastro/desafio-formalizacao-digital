package com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.NegociacaoDTO;

public class NegociacaoDTOFactory {

    public static NegociacaoDTO criarNegociacaoDTO(Long clienteId, Long cartaoId){
        return new NegociacaoDTO(clienteId, cartaoId);
    }
}
