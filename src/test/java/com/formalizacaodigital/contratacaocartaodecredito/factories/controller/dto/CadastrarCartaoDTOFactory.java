package com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CadastrarCartaoDTO;

public class CadastrarCartaoDTOFactory {

    public static CadastrarCartaoDTO criarCadastrarCartaoDTO(String categoriaCartao, Double taxaJurosCartao, Double anuidadeCartao){
        return new CadastrarCartaoDTO(categoriaCartao, taxaJurosCartao, anuidadeCartao);
    }
}
