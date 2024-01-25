package com.formalizacaodigital.contratacaocartaodecredito.gateway.converter;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.CartaoEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartaoEntityToCartaoConverter {

    private final ModelMapper mapper;

    public Cartao convert(CartaoEntity cartaoEntity){
        return mapper.map(cartaoEntity, Cartao.class);
    }
}
