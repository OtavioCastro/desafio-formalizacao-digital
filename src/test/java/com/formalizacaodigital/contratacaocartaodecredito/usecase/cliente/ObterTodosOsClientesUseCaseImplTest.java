package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.ClienteFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.impl.ObterTodosOsClientesUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class ObterTodosOsClientesUseCaseImplTest {

    @Mock
    private final ClienteGateway clienteGateway = Mockito.mock(ClienteGateway.class);

    @InjectMocks
    private final ObterTodosOsClientesUseCaseImpl obterTodosOsClientesUseCase = new ObterTodosOsClientesUseCaseImpl(clienteGateway);

    @Test
    public void deveObterTodosOsClientes(){
        List<Cliente> clientesMock = ClienteFactory.criaListaDeClientes();

        when(clienteGateway.obterTodosOsClientes()).thenReturn(clientesMock);

        List<Cliente> clientesResponse = obterTodosOsClientesUseCase.execute();

        assertFalse(clientesResponse.isEmpty());
        assertEquals(clientesMock.size(), clientesResponse.size());
        verify(clienteGateway, atLeastOnce()).obterTodosOsClientes();
    }
}
