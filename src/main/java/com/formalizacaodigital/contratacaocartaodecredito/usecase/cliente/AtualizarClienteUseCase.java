package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.AtualizarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;

public interface AtualizarClienteUseCase {
    Cliente execute(Long clienteId, AtualizarClienteDTO atualizarClienteDTO);
}
