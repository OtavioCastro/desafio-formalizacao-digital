package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;

public interface ObterClienteUseCase {
    Cliente execute(Long clienteId);
}
