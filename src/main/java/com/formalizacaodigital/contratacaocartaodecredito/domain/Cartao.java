package com.formalizacaodigital.contratacaocartaodecredito.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cartao {
    private Long cartaoId;
    private String categoriaCartao;
    private Double taxaJurosCartao;
    private Double anuidadeCartao;
}
