package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.AtualizarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;

public interface AtualizarCartaoUseCase {
    Cartao execute(Long cartaoId, AtualizarCartaoDTO atualizarCartaoDTO);
}
