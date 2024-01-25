package com.formalizacaodigital.contratacaocartaodecredito.gateway.impl;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.NegociacaoEntityToNegociacaoConverter;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.NegociacaoToNegociacaoEntityConverter;
import com.formalizacaodigital.contratacaocartaodecredito.repository.NegociacaoRepository;
import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.NegociacaoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NegociacaoGatewayImpl implements NegociacaoGateway {

    private final NegociacaoRepository negociacaoRepository;
    private final NegociacaoEntityToNegociacaoConverter toNegociacaoConverter;
    private final NegociacaoToNegociacaoEntityConverter toNegociacaoEntityConverter;

    @Override
    public List<Negociacao> obterTodasAsNegociacoes() {
        return negociacaoRepository.findAll()
                .stream()
                .map(toNegociacaoConverter::convert)
                .toList();
    }

    @Override
    public Negociacao obterNegociacao(Long negociacaoId) {
        return negociacaoRepository.findById(negociacaoId)
                .map(toNegociacaoConverter::convert)
                .orElseGet(Negociacao::new);
    }

    @Override
    public Negociacao obterNegociacaoPorCliente(Long clienteId) {
        return negociacaoRepository.findByClienteId(clienteId)
                .map(toNegociacaoConverter::convert)
                .orElseGet(Negociacao::new);
    }

    @Override
    public Negociacao realizarNegociacao(Negociacao negociacao) {
        NegociacaoEntity negociacaoEntity = toNegociacaoEntityConverter.convert(negociacao);
        return toNegociacaoConverter.convert(negociacaoRepository.save(negociacaoEntity));
    }

    @Override
    public void excluirNegociacao(Long negociacaoId) {
        negociacaoRepository.deleteById(negociacaoId);
    }
}
