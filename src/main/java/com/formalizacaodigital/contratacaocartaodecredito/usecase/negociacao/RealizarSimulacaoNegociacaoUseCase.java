package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.SimulacaoNegociacaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.SimulacaoNegociacao;

public interface RealizarSimulacaoNegociacaoUseCase {
    SimulacaoNegociacao execute(SimulacaoNegociacaoDTO simulacaoNegociacaoDTO);
}
