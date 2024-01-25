package com.formalizacaodigital.contratacaocartaodecredito.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SimulacaoNegociacao {
    private Cliente cliente;
    private List<Cartao> cartoes;
}
