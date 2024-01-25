package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;

public interface ObterNegociacaoClienteUseCase {
    Negociacao execute(Long clienteId);
}
