package com.formalizacaodigital.contratacaocartaodecredito.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Negociacao {
    private Long negociacaoId;
    private Cliente cliente;
    private Cartao cartao;
}
