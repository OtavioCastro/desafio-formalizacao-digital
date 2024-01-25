package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.SimulacaoNegociacaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.domain.SimulacaoNegociacao;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.ObterTodosOsCartoesUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.ObterClienteUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.RealizarSimulacaoNegociacaoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RealizarSimulacaoNegociacaoUseCaseImpl implements RealizarSimulacaoNegociacaoUseCase {

    private final ObterClienteUseCase obterClienteUseCase;
    private final ObterTodosOsCartoesUseCase obterTodosOsCartoesUseCase;

    @Override
    public SimulacaoNegociacao execute(SimulacaoNegociacaoDTO simulacaoNegociacaoDTO) {
        Cliente cliente = obterClienteUseCase.execute(simulacaoNegociacaoDTO.getClienteId());
        List<Cartao> cartoes = obterTodosOsCartoesUseCase.execute();
        return SimulacaoNegociacao.builder()
                .cliente(cliente)
                .cartoes(cartoes)
                .build();
    }
}
