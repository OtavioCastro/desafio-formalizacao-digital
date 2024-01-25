package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.ObterCartaoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class ObterCartaoUseCaseImpl implements ObterCartaoUseCase {

    private final CartaoGateway cartaoGateway;

    @Override
    public Cartao execute(Long cartaoId) {
        return Optional.ofNullable(cartaoGateway.obterCartao(cartaoId))
                .filter(cartao -> nonNull(cartao.getCartaoId()))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));
    }
}
