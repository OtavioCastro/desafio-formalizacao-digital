package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.ClienteFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.impl.ExcluirClienteUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ExcluirClienteUseCaseImplTest {

    @Mock
    private final ClienteGateway clienteGateway = Mockito.mock(ClienteGateway.class);

    @Mock
    private final ObterClienteUseCase obterClienteUseCase = Mockito.mock(ObterClienteUseCase.class);

    @InjectMocks
    private final ExcluirClienteUseCaseImpl excluirClienteUseCase = new ExcluirClienteUseCaseImpl(clienteGateway, obterClienteUseCase);

    @Test
    public void deveExcluirCliente(){
        Cliente clienteMock = ClienteFactory.criaCliente();

        when(obterClienteUseCase.execute(anyLong())).thenReturn(clienteMock);
        doNothing().when(clienteGateway).excluirCliente(anyLong());

        excluirClienteUseCase.execute(1L);

        verify(obterClienteUseCase, atLeastOnce()).execute(anyLong());
        verify(clienteGateway, atLeastOnce()).excluirCliente(anyLong());
    }
}
