package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.ExcluirClienteUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.ObterClienteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExcluirClienteUseCaseImpl implements ExcluirClienteUseCase {

    private final ClienteGateway clienteGateway;
    private final ObterClienteUseCase obterClienteUseCase;

    @Override
    public void execute(Long clienteId) {
        Cliente cliente = obterClienteUseCase.execute(clienteId);
        clienteGateway.excluirCliente(cliente.getClienteId());
    }
}
