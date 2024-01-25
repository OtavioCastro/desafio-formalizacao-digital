package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.ExcluirCartaoUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.ObterCartaoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExcluirCartaoUseCaseImpl implements ExcluirCartaoUseCase {

    private final CartaoGateway cartaoGateway;
    private final ObterCartaoUseCase obterCartaoUseCase;

    @Override
    public void execute(Long cartaoId) {
        Cartao cartao = obterCartaoUseCase.execute(cartaoId);
        cartaoGateway.excluirCartao(cartao.getCartaoId());
    }
}
