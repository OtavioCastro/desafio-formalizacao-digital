package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CadastrarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.CadastrarCartaoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastrarCartaoUseCaseImpl implements CadastrarCartaoUseCase {

    private final CartaoGateway cartaoGateway;

    @Override
    public Cartao execute(CadastrarCartaoDTO cadastrarCartaoDTO) {
        Cartao cartao = Cartao.builder()
                .categoriaCartao(cadastrarCartaoDTO.getCategoriaCartao())
                .anuidadeCartao(cadastrarCartaoDTO.getAnuidadeCartao())
                .taxaJurosCartao(cadastrarCartaoDTO.getTaxaJurosCartao())
                .build();
        return cartaoGateway.salvarCartao(cartao);
    }
}
