package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;

public interface ObterNegociacaoUseCase {
    Negociacao execute(Long negociacaoId);
}
