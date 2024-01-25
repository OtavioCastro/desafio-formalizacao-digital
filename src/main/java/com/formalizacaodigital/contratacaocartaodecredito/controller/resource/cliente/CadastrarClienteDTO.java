package com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarClienteDTO {
    private String nome;
    private String documento;
    private String telefone;
}
