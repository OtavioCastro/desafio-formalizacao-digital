package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.ObterTodasAsNegociacoesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ObterTodasAsNegociacoesUseCaseImpl implements ObterTodasAsNegociacoesUseCase {

    private final NegociacaoGateway negociacaoGateway;

    @Override
    public List<Negociacao> execute() {
        return negociacaoGateway.obterTodasAsNegociacoes();
    }
}
