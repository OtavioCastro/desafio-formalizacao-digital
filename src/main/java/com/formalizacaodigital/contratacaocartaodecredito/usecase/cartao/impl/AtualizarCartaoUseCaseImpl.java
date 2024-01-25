package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.AtualizarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.AtualizarCartaoUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.ObterCartaoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class AtualizarCartaoUseCaseImpl implements AtualizarCartaoUseCase {

    private final CartaoGateway cartaoGateway;
    private final ObterCartaoUseCase obterCartaoUseCase;

    @Override
    public Cartao execute(Long cartaoId, AtualizarCartaoDTO atualizarCartaoDTO) {
        Cartao cartao = obterCartaoUseCase.execute(cartaoId);
        Cartao cartaoAtualizado = atualizaDadosCartao(cartao, atualizarCartaoDTO);
        return cartaoGateway.salvarCartao(cartaoAtualizado);
    }

    private Cartao atualizaDadosCartao(Cartao cartao, AtualizarCartaoDTO atualizarCartaoDTO) {
        return Cartao.builder()
                .cartaoId(cartao.getCartaoId())
                .categoriaCartao(cartao.getCategoriaCartao())
                .anuidadeCartao(isNull(atualizarCartaoDTO.getAnuidadeCartao()) ? cartao.getAnuidadeCartao() : atualizarCartaoDTO.getAnuidadeCartao())
                .taxaJurosCartao(isNull(atualizarCartaoDTO.getTaxaJurosCartao()) ? cartao.getTaxaJurosCartao() : atualizarCartaoDTO.getTaxaJurosCartao())
                .build();
    }
}
