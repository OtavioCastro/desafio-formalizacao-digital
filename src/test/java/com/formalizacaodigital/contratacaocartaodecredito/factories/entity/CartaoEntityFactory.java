package com.formalizacaodigital.contratacaocartaodecredito.factories.entity;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.CartaoEntity;

import java.util.List;

public class CartaoEntityFactory {

    public static CartaoEntity criaCartao() {
        CartaoEntity cartao = new CartaoEntity();
        cartao.setCartaoId(1L);
        cartao.setCategoriaCartao("BRONZE");
        cartao.setAnuidadeCartao(1000.00);
        cartao.setTaxaJurosCartao(2.00);
        return cartao;
    }

    public static List<CartaoEntity> criaListaDeCartoesEntity(){
        CartaoEntity cartaoBronze = new CartaoEntity();
        cartaoBronze.setCartaoId(1L);
        cartaoBronze.setCategoriaCartao("BRONZE");
        cartaoBronze.setAnuidadeCartao(1000.00);
        cartaoBronze.setTaxaJurosCartao(2.00);

        CartaoEntity cartaoPrata = new CartaoEntity();
        cartaoPrata.setCartaoId(2L);
        cartaoPrata.setCategoriaCartao("PRATA");
        cartaoPrata.setAnuidadeCartao(2000.00);
        cartaoPrata.setTaxaJurosCartao(1.79);

        CartaoEntity cartaoOuro = new CartaoEntity();
        cartaoOuro.setCartaoId(3L);
        cartaoOuro.setCategoriaCartao("OURO");
        cartaoOuro.setAnuidadeCartao(3000.00);
        cartaoOuro.setTaxaJurosCartao(1.39);

        return List.of(cartaoBronze, cartaoPrata, cartaoOuro);
    }
}
