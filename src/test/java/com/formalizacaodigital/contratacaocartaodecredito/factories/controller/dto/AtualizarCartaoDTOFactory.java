package com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.AtualizarCartaoDTO;

public class AtualizarCartaoDTOFactory {

    public static AtualizarCartaoDTO criaAtualizarCartaoDTO(Double taxaJurosCartao, Double anuidadeCartao){
        return new AtualizarCartaoDTO(taxaJurosCartao, anuidadeCartao);
    }
}
