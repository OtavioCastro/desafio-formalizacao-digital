package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.ObterTodosOsCartoesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ObterTodosOsCartoesUseCaseImpl implements ObterTodosOsCartoesUseCase {

    private final CartaoGateway cartaoGateway;

    @Override
    public List<Cartao> execute() {
        return Optional.ofNullable(cartaoGateway.obterTodosOsCartoes())
                .filter(lista -> !lista.isEmpty())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Não há nenhum cartão cadastrado."));
    }
}
