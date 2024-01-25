package com.formalizacaodigital.contratacaocartaodecredito.gateway;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.ClienteFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.entity.ClienteEntityFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.ClienteEntityToClienteConverter;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.ClienteToClienteEntityConverter;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.impl.ClienteGatewayImpl;
import com.formalizacaodigital.contratacaocartaodecredito.repository.ClienteRepository;
import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.ClienteEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteGatewayImplTest {

    @Mock
    private final ClienteRepository clienteRepository = Mockito.mock(ClienteRepository.class);

    @Spy
    private final ClienteEntityToClienteConverter toClienteConverter = new ClienteEntityToClienteConverter(new ModelMapper());

    @Spy
    private final ClienteToClienteEntityConverter toClienteEntityConverter = new ClienteToClienteEntityConverter(new ModelMapper());

    @InjectMocks
    private final ClienteGatewayImpl clienteGateway = new ClienteGatewayImpl(clienteRepository, toClienteConverter, toClienteEntityConverter);

    @Test
    public void deveObterTodosOsClientesEntity(){
        List<ClienteEntity> listaDeClientesEntityMock = ClienteEntityFactory.criaListaDeClientesEntity();

        when(clienteRepository.findAll()).thenReturn(listaDeClientesEntityMock);

        List<Cliente> listaDeClientesResponse = clienteGateway.obterTodosOsClientes();

        assertEquals(listaDeClientesEntityMock.size(), listaDeClientesResponse.size());
        verify(clienteRepository, atLeastOnce()).findAll();
    }

    @Test
    public void deveRetornarListaVaziaDeCartoesEntity(){
        List<Cliente> listaDeClientesResponse = clienteGateway.obterTodosOsClientes();
        assertTrue(listaDeClientesResponse.isEmpty());
    }

    @Test
    public void deveObterClienteEntity(){
        ClienteEntity clienteEntityMock = ClienteEntityFactory.criaCliente();

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteEntityMock));

        Cliente clienteResponse = clienteGateway.obterCliente(1L);

        assertEquals(clienteEntityMock.getClienteId(), clienteResponse.getClienteId());
        verify(clienteRepository, atLeastOnce()).findById(anyLong());
    }

    @Test
    public void deveRetornarClienteVazio(){
        Cliente clienteResponse = clienteGateway.obterCliente(1L);
        assertNull(clienteResponse.getClienteId());
    }

    @Test
    public void deveSalvarCliente(){
        Cliente clienteMock = ClienteFactory.criaClienteSemId();
        ClienteEntity clienteSalvo = ClienteEntityFactory.criaCliente();

        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(clienteSalvo);

        Cliente clienteResponse = clienteGateway.salvarCliente(clienteMock);

        assertEquals(clienteSalvo.getClienteId(), clienteResponse.getClienteId());
        verify(clienteRepository, atLeastOnce()).save(any(ClienteEntity.class));
    }

    @Test
    public void deveExcluirCliente(){

        doNothing().when(clienteRepository).deleteById(anyLong());

        clienteGateway.excluirCliente(1L);

        verify(clienteRepository, atLeastOnce()).deleteById(anyLong());
    }

}
