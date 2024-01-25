package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.ObterTodosOsClientesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ObterTodosOsClientesUseCaseImpl implements ObterTodosOsClientesUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public List<Cliente> execute() {
        return clienteGateway.obterTodosOsClientes();
    }
}
