package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.ClienteFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.impl.ObterClienteUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ObterClienteUseCaseImplTest {

    @Mock
    private final ClienteGateway clienteGateway = Mockito.mock(ClienteGateway.class);

    @InjectMocks
    private final ObterClienteUseCaseImpl obterClienteUseCase = new ObterClienteUseCaseImpl(clienteGateway);

    @Test
    public void deveObterCliente(){
        Cliente clienteMock = ClienteFactory.criaCliente();

        when(clienteGateway.obterCliente(anyLong())).thenReturn(clienteMock);

        Cliente clienteResponse = obterClienteUseCase.execute(1L);

        assertEquals(clienteMock.getClienteId(), clienteResponse.getClienteId());
        verify(clienteGateway, atLeastOnce()).obterCliente(anyLong());
    }

    @Test
    public void deveLancarExcecaoDeClienteNaoEncontrado(){

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> obterClienteUseCase.execute(1L));

        assertEquals("Cliente não encontrado", exception.getStatusText());
        verify(clienteGateway, atLeastOnce()).obterCliente(anyLong());
    }
}
