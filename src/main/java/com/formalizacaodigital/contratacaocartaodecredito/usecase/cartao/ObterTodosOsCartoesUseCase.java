package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;

import java.util.List;

public interface ObterTodosOsCartoesUseCase {
    List<Cartao> execute();
}
