package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;

public interface ObterCartaoUseCase {
    Cartao execute(Long cartaoId);
}
