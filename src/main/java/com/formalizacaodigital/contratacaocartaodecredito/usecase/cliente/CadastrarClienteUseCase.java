package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.CadastrarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;

public interface CadastrarClienteUseCase {
    Cliente execute(CadastrarClienteDTO cadastrarClienteDTO);
}
