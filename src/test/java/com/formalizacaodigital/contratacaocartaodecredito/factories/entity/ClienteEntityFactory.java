package com.formalizacaodigital.contratacaocartaodecredito.factories.entity;

import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.ClienteEntity;

import java.util.List;

public class ClienteEntityFactory {

    public static ClienteEntity criaCliente(){
        ClienteEntity cliente = new ClienteEntity();
        cliente.setClienteId(1L);
        cliente.setNome("Fulano da Silva");
        cliente.setTelefone("11989897878");
        cliente.setDocumento("12345678900");
        return cliente;
    }

    public static List<ClienteEntity> criaListaDeClientesEntity(){
        ClienteEntity cliente1 = new ClienteEntity();
        cliente1.setClienteId(1L);
        cliente1.setNome("Fulano da Silva");
        cliente1.setTelefone("11989897878");
        cliente1.setDocumento("12345678900");

        ClienteEntity cliente2 = new ClienteEntity();
        cliente2.setClienteId(2L);
        cliente2.setNome("Ciclano de Souza");
        cliente2.setTelefone("11952526161");
        cliente2.setDocumento("09876543210");

        return List.of(cliente1, cliente2);
    }
}
