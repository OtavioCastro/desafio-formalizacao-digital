package com.formalizacaodigital.contratacaocartaodecredito.controller;

import com.formalizacaodigital.contratacaocartaodecredito.controller.converter.ClienteToClienteResourceConverter;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.AtualizarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.CadastrarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.ClienteResource;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.AtualizarClienteDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.CadastrarClienteDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.ClienteFactory;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteControllerTest {

    @Mock
    private final AtualizarClienteUseCase atualizarClienteUseCase = Mockito.mock(AtualizarClienteUseCase.class);

    @Mock
    private final CadastrarClienteUseCase cadastrarClienteUseCase = Mockito.mock(CadastrarClienteUseCase.class);

    @Mock
    private final ExcluirClienteUseCase excluirClienteUseCase = Mockito.mock(ExcluirClienteUseCase.class);

    @Mock
    private final ObterClienteUseCase obterClienteUseCase = Mockito.mock(ObterClienteUseCase.class);

    @Mock
    private final ObterTodosOsClientesUseCase obterTodosOsClientesUseCase = Mockito.mock(ObterTodosOsClientesUseCase.class);

    @Spy
    private final ClienteToClienteResourceConverter toClienteResourceConverter = new ClienteToClienteResourceConverter(new ModelMapper());

    @InjectMocks
    public final ClienteController clienteController = new ClienteController(
            atualizarClienteUseCase,
            cadastrarClienteUseCase,
            excluirClienteUseCase,
            obterClienteUseCase,
            obterTodosOsClientesUseCase,
            toClienteResourceConverter
    );

    @Test
    public void deveObterTodosOsClientes(){
        List<Cliente> listaDeClientesMock = ClienteFactory.criaListaDeClientes();

        when(obterTodosOsClientesUseCase.execute()).thenReturn(listaDeClientesMock);

        List<ClienteResource> listaDeClientesResponse = clienteController.obterTodosOsClientes();

        assertEquals(listaDeClientesMock.size(), listaDeClientesResponse.size());
        verify(obterTodosOsClientesUseCase, atLeastOnce()).execute();
    }

    @Test
    public void deveObterCliente(){
        Cliente clienteMock = ClienteFactory.criaCliente();

        when(obterClienteUseCase.execute(anyLong())).thenReturn(clienteMock);

        ClienteResource clienteResponse = clienteController.obterCliente(1L);

        assertEquals(clienteMock.getClienteId(), clienteResponse.getClienteId());
        verify(obterClienteUseCase, atLeastOnce()).execute(anyLong());
    }

    @Test
    public void deveCadastrarCliente(){
        CadastrarClienteDTO cadastrarClienteDTOMock = CadastrarClienteDTOFactory.criaCadastrarClienteDTO("Fulano da Silva", "12345678900", "11989897878");
        Cliente clienteMock = ClienteFactory.criaCliente();

        when(cadastrarClienteUseCase.execute(any(CadastrarClienteDTO.class))).thenReturn(clienteMock);

        ClienteResource clienteResponse = clienteController.cadastrarCliente(cadastrarClienteDTOMock);

        assertEquals(clienteMock.getClienteId(), clienteResponse.getClienteId());
        verify(cadastrarClienteUseCase, atLeastOnce()).execute(any(CadastrarClienteDTO.class));
    }

    @Test
    public void deveAtualizarCliente(){
        AtualizarClienteDTO atualizarClienteDTOMock = AtualizarClienteDTOFactory.criaAtualizarClienteDTO("11988552211");
        Cliente clienteMock = ClienteFactory.criaClienteAtualizado(atualizarClienteDTOMock.getTelefone());

        when(atualizarClienteUseCase.execute(anyLong(), any(AtualizarClienteDTO.class))).thenReturn(clienteMock);

        ClienteResource clienteResponse = clienteController.atualizarCliente(clienteMock.getClienteId(), atualizarClienteDTOMock);

        assertEquals(clienteResponse.getClienteId(), clienteMock.getClienteId());
        assertEquals(atualizarClienteDTOMock.getTelefone(), clienteResponse.getTelefone());
        verify(atualizarClienteUseCase, atLeastOnce()).execute(anyLong(), any(AtualizarClienteDTO.class));
    }

    @Test
    public void deveExcluirCliente(){

        doNothing().when(excluirClienteUseCase).execute(anyLong());

        clienteController.excluirCliente(1L);

        verify(excluirClienteUseCase, atLeastOnce()).execute(anyLong());
    }
}
