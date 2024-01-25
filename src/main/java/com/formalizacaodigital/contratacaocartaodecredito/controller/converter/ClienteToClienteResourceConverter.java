package com.formalizacaodigital.contratacaocartaodecredito.controller.converter;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.ClienteResource;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClienteToClienteResourceConverter {

    private final ModelMapper mapper;

    public ClienteResource convert(Cliente cliente){
        return mapper.map(cliente, ClienteResource.class);
    }
}
