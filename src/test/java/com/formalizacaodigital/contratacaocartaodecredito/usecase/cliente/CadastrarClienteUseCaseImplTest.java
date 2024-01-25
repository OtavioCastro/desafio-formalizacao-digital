package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.CadastrarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.CadastrarClienteDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.ClienteFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.impl.CadastrarClienteUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CadastrarClienteUseCaseImplTest {

    @Mock
    private final ClienteGateway clienteGateway = Mockito.mock(ClienteGateway.class);

    @InjectMocks
    private final CadastrarClienteUseCaseImpl cadastrarClienteUseCase = new CadastrarClienteUseCaseImpl(clienteGateway);

    @Test
    public void deveCadastrarCliente(){
        Cliente clienteMock = ClienteFactory.criaCliente();
        CadastrarClienteDTO cadastrarClienteDTOMock = CadastrarClienteDTOFactory.criaCadastrarClienteDTO("Fulano da Silva", "12345678900", "11989897878");

        when(clienteGateway.salvarCliente(any(Cliente.class))).thenReturn(clienteMock);

        Cliente clienteResponse = cadastrarClienteUseCase.execute(cadastrarClienteDTOMock);

        assertEquals(clienteMock.getNome(), clienteResponse.getNome());
        assertEquals(clienteMock.getDocumento(), clienteResponse.getDocumento());
        assertEquals(clienteMock.getTelefone(), clienteResponse.getTelefone());
        verify(clienteGateway, atLeastOnce()).salvarCliente(any(Cliente.class));
    }
}
