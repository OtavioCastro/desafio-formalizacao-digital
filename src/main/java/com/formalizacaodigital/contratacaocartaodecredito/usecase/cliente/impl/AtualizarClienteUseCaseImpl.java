package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.impl;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.AtualizarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.AtualizarClienteUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.ObterClienteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AtualizarClienteUseCaseImpl implements AtualizarClienteUseCase {

    private final ClienteGateway clienteGateway;
    private final ObterClienteUseCase obterClienteUseCase;

    @Override
    public Cliente execute(Long clienteId, AtualizarClienteDTO atualizarClienteDTO) {
        Cliente cliente = obterClienteUseCase.execute(clienteId);
        Cliente clienteAtualizado = atualizaDadosCliente(cliente, atualizarClienteDTO);
        return clienteGateway.salvarCliente(clienteAtualizado);
    }

    private Cliente atualizaDadosCliente(Cliente cliente, AtualizarClienteDTO atualizarClienteDTO) {
        return cliente.withTelefone(atualizarClienteDTO.getTelefone());
        /*return Cliente.builder()
                .telefone(atualizarClienteDTO.getTelefone())
                .clienteId(cliente.getClienteId())
                .nome(cliente.getNome())
                .documento(cliente.getDocumento())
                .build();*/
    }
}
