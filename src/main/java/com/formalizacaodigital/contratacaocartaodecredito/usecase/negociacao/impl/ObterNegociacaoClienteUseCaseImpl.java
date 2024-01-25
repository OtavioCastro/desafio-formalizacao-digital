package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.ObterNegociacaoClienteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class ObterNegociacaoClienteUseCaseImpl implements ObterNegociacaoClienteUseCase {

    private final NegociacaoGateway negociacaoGateway;

    @Override
    public Negociacao execute(Long clienteId) {
        return Optional.ofNullable(negociacaoGateway.obterNegociacaoPorCliente(clienteId))
                .filter(negociacao -> nonNull(negociacao.getNegociacaoId()))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Negociação não encontrada"));
    }
}
