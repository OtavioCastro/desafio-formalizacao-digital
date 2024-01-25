package com.formalizacaodigital.contratacaocartaodecredito.gateway;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;

import java.util.List;

public interface CartaoGateway {
    List<Cartao> obterTodosOsCartoes();
    Cartao obterCartao(Long cartaoId);
    Cartao salvarCartao(Cartao cartao);
    void excluirCartao(Long cartaoId);
}
