package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.NegociacaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;

public interface RealizarNegociacaoUseCase {
    Negociacao execute(NegociacaoDTO negociacaoDTO);
}
