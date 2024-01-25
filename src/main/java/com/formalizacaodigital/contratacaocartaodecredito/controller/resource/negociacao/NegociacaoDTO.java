package com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NegociacaoDTO {
    private Long clienteId;
    private Long cartaoId;
}
