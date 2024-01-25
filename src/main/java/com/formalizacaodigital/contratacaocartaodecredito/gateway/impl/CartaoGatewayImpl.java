package com.formalizacaodigital.contratacaocartaodecredito.gateway.impl;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.AtualizarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CadastrarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.CartaoEntityToCartaoConverter;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.CartaoToCartaoEntityConverter;
import com.formalizacaodigital.contratacaocartaodecredito.repository.CartaoRepository;
import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.CartaoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CartaoGatewayImpl implements CartaoGateway {

    private final CartaoRepository cartaoRepository;
    private final CartaoEntityToCartaoConverter toCartaoConverter;
    private final CartaoToCartaoEntityConverter toCartaoEntityConverter;

    @Override
    public List<Cartao> obterTodosOsCartoes() {
        return cartaoRepository.findAll()
                .stream()
                .map(toCartaoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Cartao obterCartao(Long cartaoId) {
        return cartaoRepository.findById(cartaoId)
                .map(toCartaoConverter::convert)
                .orElseGet(Cartao::new);
    }

    @Override
    public Cartao salvarCartao(Cartao cartao) {
        CartaoEntity cartaoEntity = toCartaoEntityConverter.convert(cartao);
        return toCartaoConverter.convert(cartaoRepository.save(cartaoEntity));
    }

    @Override
    public void excluirCartao(Long cartaoId) {
        cartaoRepository.deleteById(cartaoId);
    }
}
