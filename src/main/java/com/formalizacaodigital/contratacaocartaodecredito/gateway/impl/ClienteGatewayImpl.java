package com.formalizacaodigital.contratacaocartaodecredito.gateway.impl;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.AtualizarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.CadastrarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.ClienteEntityToClienteConverter;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.ClienteToClienteEntityConverter;
import com.formalizacaodigital.contratacaocartaodecredito.repository.ClienteRepository;
import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.ClienteEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteRepository clienteRepository;
    private final ClienteEntityToClienteConverter toClienteConverter;
    private final ClienteToClienteEntityConverter toClienteEntityConverter;

    @Override
    public List<Cliente> obterTodosOsClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(toClienteConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Cliente obterCliente(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(toClienteConverter::convert)
                .orElseGet(Cliente::new);
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = toClienteEntityConverter.convert(cliente);
        return toClienteConverter.convert(clienteRepository.save(clienteEntity));
    }

    @Override
    public void excluirCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
