package com.formalizacaodigital.contratacaocartaodecredito.controller.converter;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CartaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartaoToCartaoResourceConverter {

    private final ModelMapper mapper;

    public CartaoResource convert(Cartao cartao){
        return mapper.map(cartao, CartaoResource.class);
    }
}
