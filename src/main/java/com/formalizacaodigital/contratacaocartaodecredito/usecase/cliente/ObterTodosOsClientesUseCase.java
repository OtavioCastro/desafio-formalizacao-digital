package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;

import java.util.List;

public interface ObterTodosOsClientesUseCase {
    List<Cliente> execute();
}
