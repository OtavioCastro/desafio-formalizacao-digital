package com.formalizacaodigital.contratacaocartaodecredito.gateway;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;

import java.util.List;

public interface ClienteGateway {
    List<Cliente> obterTodosOsClientes();
    Cliente obterCliente(Long clienteId);
    Cliente salvarCliente(Cliente cadastrarClienteDTO);
    void excluirCliente(Long clienteId);
}
