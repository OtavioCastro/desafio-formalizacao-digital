package com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.AtualizarClienteDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.ClienteFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.ClienteGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.impl.AtualizarClienteUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AtualizarClienteUseCaseImplTest {

    @Mock
    private final ClienteGateway clienteGateway = Mockito.mock(ClienteGateway.class);

    @Mock
    private final ObterClienteUseCase obterClienteUseCase = Mockito.mock(ObterClienteUseCase.class);

    @InjectMocks
    private final AtualizarClienteUseCaseImpl atualizarClienteUseCase = new AtualizarClienteUseCaseImpl(clienteGateway, obterClienteUseCase);

    @Test
    public void deveAtualizarTelefoneDoCliente(){
        Cliente clienteMock = ClienteFactory.criaCliente();
        String novoTelefone = "956564545";
        Cliente clienteSalvo = ClienteFactory.criaClienteAtualizado(novoTelefone);

        when(obterClienteUseCase.execute(anyLong())).thenReturn(clienteMock);
        when(clienteGateway.salvarCliente(any(Cliente.class))).thenReturn(clienteSalvo);

        Cliente clienteResponse = atualizarClienteUseCase.execute(1L, AtualizarClienteDTOFactory.criaAtualizarClienteDTO(novoTelefone));

        assertEquals(clienteMock.getClienteId(), clienteResponse.getClienteId());
        assertNotEquals(clienteMock.getTelefone(), clienteResponse.getTelefone());
        verify(obterClienteUseCase, atLeastOnce()).execute(anyLong());
        verify(clienteGateway, atLeastOnce()).salvarCliente(any(Cliente.class));
    }
}
