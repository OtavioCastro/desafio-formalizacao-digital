package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CadastrarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;

public interface CadastrarCartaoUseCase {
    Cartao execute(CadastrarCartaoDTO cadastrarCartaoDTO);
}
