package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;

import java.util.List;

public interface ObterTodasAsNegociacoesUseCase {
    List<Negociacao> execute();
}
