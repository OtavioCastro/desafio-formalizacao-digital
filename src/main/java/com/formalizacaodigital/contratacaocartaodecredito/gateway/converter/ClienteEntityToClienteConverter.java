package com.formalizacaodigital.contratacaocartaodecredito.gateway.converter;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.ClienteEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClienteEntityToClienteConverter {

    private final ModelMapper mapper;

    public Cliente convert(ClienteEntity clienteEntity){
        return mapper.map(clienteEntity, Cliente.class);
    }
}
