package com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.CadastrarClienteDTO;

public class CadastrarClienteDTOFactory {

    public static CadastrarClienteDTO criaCadastrarClienteDTO(String nome, String documento, String telefone) {
        return new CadastrarClienteDTO(nome, documento, telefone);
    }
}
