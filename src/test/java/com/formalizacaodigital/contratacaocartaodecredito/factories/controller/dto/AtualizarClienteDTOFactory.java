package com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.AtualizarClienteDTO;

public class AtualizarClienteDTOFactory {

    public static AtualizarClienteDTO criaAtualizarClienteDTO(String telefone){
        return new AtualizarClienteDTO(telefone);
    }
}
