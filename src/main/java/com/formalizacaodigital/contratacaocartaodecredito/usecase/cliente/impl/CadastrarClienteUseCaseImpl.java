package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.impl;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.CadastrarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.CadastrarClienteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastrarClienteUseCaseImpl implements CadastrarClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public Cliente execute(CadastrarClienteDTO cadastrarClienteDTO) {
        Cliente cliente = Cliente.builder()
                .nome(cadastrarClienteDTO.getNome())
                .documento(cadastrarClienteDTO.getDocumento())
                .telefone(cadastrarClienteDTO.getTelefone())
                .build();
        return clienteGateway.salvarCliente(cliente);
    }
}
