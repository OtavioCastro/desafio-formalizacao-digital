package com.formalizacaodigital.contratacaocartaodecredito.factories.domain;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.domain.SimulacaoNegociacao;

import java.util.List;

public class NegociacaoFactory {

    public static Negociacao criaNegociacao(){
        return Negociacao.builder()
                .negociacaoId(1L)
                .cliente(ClienteFactory.criaCliente())
                .cartao(CartaoFactory.criaCartao())
                .build();
    }

    public static List<Negociacao> criaListaDeNegociacao(){
        return List.of(
                Negociacao.builder()
                        .negociacaoId(1L)
                        .cliente(ClienteFactory.criaCliente())
                        .cartao(CartaoFactory.criaCartao())
                        .build(),
                Negociacao.builder()
                        .negociacaoId(2L)
                        .cliente(ClienteFactory.criaClienteAtualizado("1158513577"))
                        .cartao(CartaoFactory.criaCartaoAtualizado(2900.9, 5.0))
                        .build()
        );
    }

    public static Negociacao criaNegociacaoSemId(){
        return Negociacao.builder()
                .cliente(ClienteFactory.criaCliente())
                .cartao(CartaoFactory.criaCartao())
                .build();
    }

    public static SimulacaoNegociacao criaSimulacaoNegociacao(){
        return SimulacaoNegociacao.builder()
                .cliente(ClienteFactory.criaCliente())
                .cartoes(CartaoFactory.criaListaDeCartoes())
                .build();
    }

}
