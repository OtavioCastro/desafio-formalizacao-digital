package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.ObterClienteUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.ObterNegociacaoClienteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class ObterClienteUseCaseImpl implements ObterClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public Cliente execute(Long clienteId) {
        return Optional.ofNullable(clienteGateway.obterCliente(clienteId))
                .filter(clienteResponse -> nonNull(clienteResponse.getClienteId()))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
}
