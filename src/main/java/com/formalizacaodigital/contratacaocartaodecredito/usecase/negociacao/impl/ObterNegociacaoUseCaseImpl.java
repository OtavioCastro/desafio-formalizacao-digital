package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.ObterNegociacaoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class ObterNegociacaoUseCaseImpl implements ObterNegociacaoUseCase {

    private final NegociacaoGateway negociacaoGateway;

    @Override
    public Negociacao execute(Long negociacaoId) {
        return Optional.ofNullable(negociacaoGateway.obterNegociacao(negociacaoId))
                .filter(negociacao -> nonNull(negociacao.getNegociacaoId()))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Negociação não encontrada"));
    }
}
