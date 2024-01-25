package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.ExcluirNegociacaoUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.ObterNegociacaoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExcluirNegociacaoUseCaseImpl implements ExcluirNegociacaoUseCase {

    private final NegociacaoGateway negociacaoGateway;
    private final ObterNegociacaoUseCase obterNegociacaoUseCase;

    @Override
    public void execute(Long negociacaoId) {
        Negociacao negociacao = obterNegociacaoUseCase.execute(negociacaoId);
        negociacaoGateway.excluirNegociacao(negociacao.getNegociacaoId());
    }
}
