package com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarCartaoDTO {
    private String categoriaCartao;
    private Double taxaJurosCartao;
    private Double anuidadeCartao;
}
