package com.formalizacaodigital.contratacaocartaodecredito.gateway;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;

import java.util.List;

public interface NegociacaoGateway {
    List<Negociacao> obterTodasAsNegociacoes();
    Negociacao obterNegociacao(Long negociacaoId);
    Negociacao obterNegociacaoPorCliente(Long clienteId);
    Negociacao realizarNegociacao(Negociacao negociacao);
    void excluirNegociacao(Long negociacaoId);
}
