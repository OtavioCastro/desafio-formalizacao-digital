package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.NegociacaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.ObterCartaoUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.AtualizarClienteUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.ObterClienteUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.RealizarNegociacaoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RealizarNegociacaoUseCaseImpl implements RealizarNegociacaoUseCase {

    private final NegociacaoGateway negociacaoGateway;
    private final ObterClienteUseCase obterClienteUseCase;
    private final ObterCartaoUseCase obterCartaoUseCase;

    @Override
    public Negociacao execute(NegociacaoDTO negociacaoDTO) {
        Cliente cliente = obterClienteUseCase.execute(negociacaoDTO.getClienteId());
        Cartao cartao = obterCartaoUseCase.execute(negociacaoDTO.getCartaoId());
        Negociacao negociacao = Negociacao.builder()
                .cliente(cliente)
                .cartao(cartao)
                .build();
        return negociacaoGateway.realizarNegociacao(negociacao);
    }

}
