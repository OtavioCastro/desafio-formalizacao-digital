package com.formalizacaodigital.contratacaocartaodecredito.factories.domain;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;

import java.util.List;

public class ClienteFactory {

    public static Cliente criaCliente(){
        return Cliente.builder()
                .clienteId(1L)
                .nome("Fulano da Silva")
                .telefone("11989897878")
                .documento("12345678900")
                .build();
    }

    public static Cliente criaClienteSemId(){
        return Cliente.builder()
                .nome("Fulano da Silva")
                .telefone("11989897878")
                .documento("12345678900")
                .build();
    }

    public static List<Cliente> criaListaDeClientes(){
        return List.of(
                Cliente.builder()
                        .clienteId(1L)
                        .nome("Fulano da Silva")
                        .telefone("11989897878")
                        .documento("12345678900")
                        .build(),
                Cliente.builder()
                        .clienteId(2L)
                        .nome("Ciclano de Souza")
                        .telefone("11952526161")
                        .documento("09876543210")
                        .build()
        );
    }

    public static Cliente criaClienteAtualizado(String telefone){
        return ClienteFactory.criaCliente().withTelefone(telefone);
    }
}
