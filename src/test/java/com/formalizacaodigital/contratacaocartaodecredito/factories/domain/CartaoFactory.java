package com.formalizacaodigital.contratacaocartaodecredito.factories.domain;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;

import java.util.List;

public class CartaoFactory {

    public static Cartao criaCartao(){
        return Cartao.builder()
                .cartaoId(1L)
                .categoriaCartao("BRONZE")
                .anuidadeCartao(1000.00)
                .taxaJurosCartao(2.00)
                .build();
    }

    public static Cartao criaCartaoSemId(){
        return Cartao.builder()
                .categoriaCartao("BRONZE")
                .anuidadeCartao(1000.00)
                .taxaJurosCartao(2.00)
                .build();
    }

    public static List<Cartao> criaListaDeCartoes(){
        return List.of(
                Cartao.builder()
                        .cartaoId(1L)
                        .categoriaCartao("BRONZE")
                        .anuidadeCartao(1000.00)
                        .taxaJurosCartao(2.00)
                        .build(),
                Cartao.builder()
                        .cartaoId(2L)
                        .categoriaCartao("PRATA")
                        .anuidadeCartao(3000.00)
                        .taxaJurosCartao(1.60)
                        .build(),
                Cartao.builder()
                        .cartaoId(3L)
                        .categoriaCartao("OURO")
                        .anuidadeCartao(7000.00)
                        .taxaJurosCartao(0.85)
                        .build()
        );
    }

    public static Cartao criaCartaoAtualizado(Double anuidadeCartao, Double taxaJuros) {
        Cartao cartao = CartaoFactory.criaCartao();
        cartao.setAnuidadeCartao(anuidadeCartao);
        cartao.setTaxaJurosCartao(taxaJuros);
        return cartao;
    }
}
