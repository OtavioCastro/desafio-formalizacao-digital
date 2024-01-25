package com.formalizacaodigital.contratacaocartaodecredito.factories.entity;

import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.NegociacaoEntity;

import java.util.List;

public class NegociacaoEntityFactory {

    public static NegociacaoEntity criaNegociacao(){
        NegociacaoEntity negociacao = new NegociacaoEntity();
        negociacao.setNegociacaoId(1L);
        negociacao.setCliente(ClienteEntityFactory.criaCliente());
        negociacao.setCartao(CartaoEntityFactory.criaCartao());
        return negociacao;
    }

    public static List<NegociacaoEntity> criaListaDeNegociacao(){
        NegociacaoEntity negociacao1 = new NegociacaoEntity();
        negociacao1.setNegociacaoId(1L);
        negociacao1.setCliente(ClienteEntityFactory.criaCliente());
        negociacao1.setCartao(CartaoEntityFactory.criaCartao());

        NegociacaoEntity negociacao2 = new NegociacaoEntity();
        negociacao2.setNegociacaoId(1L);
        negociacao2.setCliente(ClienteEntityFactory.criaCliente());
        negociacao2.setCartao(CartaoEntityFactory.criaCartao());

        return List.of(negociacao1, negociacao2);
    }

}
