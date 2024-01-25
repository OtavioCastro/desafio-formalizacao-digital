package com.formalizacaodigital.contratacaocartaodecredito.controller;

import com.formalizacaodigital.contratacaocartaodecredito.controller.converter.NegociacaoToNegociacaoResourceConverter;
import com.formalizacaodigital.contratacaocartaodecredito.controller.converter.SimulacaoNegociacaoToSimulacaoNegociacaoResourceConverter;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.NegociacaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.NegociacaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.SimulacaoNegociacaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.SimulacaoNegociacaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.domain.SimulacaoNegociacao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.NegociacaoDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.SimulacaoNegociacaoDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.NegociacaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NegociacaoControllerTest {

    @Mock
    private final RealizarSimulacaoNegociacaoUseCase realizarSimulacaoNegociacaoUseCase = Mockito.mock(RealizarSimulacaoNegociacaoUseCase.class);

    @Mock
    private final ObterTodasAsNegociacoesUseCase obterTodasAsNegociacoesUseCase = Mockito.mock(ObterTodasAsNegociacoesUseCase.class);

    @Mock
    private final ObterNegociacaoUseCase obterNegociacaoUseCase = Mockito.mock(ObterNegociacaoUseCase.class);

    @Mock
    private final ObterNegociacaoClienteUseCase obterNegociacaoClienteUseCase = Mockito.mock(ObterNegociacaoClienteUseCase.class);

    @Mock
    private final ExcluirNegociacaoUseCase excluirNegociacaoUseCase = Mockito.mock(ExcluirNegociacaoUseCase.class);

    @Mock
    private final RealizarNegociacaoUseCase realizarNegociacaoUseCase = Mockito.mock(RealizarNegociacaoUseCase.class);

    @Spy
    private final SimulacaoNegociacaoToSimulacaoNegociacaoResourceConverter toSimulacaoNegociacaoResourceConverter =
            new SimulacaoNegociacaoToSimulacaoNegociacaoResourceConverter(new ModelMapper());

    @Spy
    private final NegociacaoToNegociacaoResourceConverter toNegociacaoResourceConverter =
            new NegociacaoToNegociacaoResourceConverter(new ModelMapper());

    @InjectMocks
    private NegociacaoController negociacaoController = new NegociacaoController(
            realizarSimulacaoNegociacaoUseCase,
            obterTodasAsNegociacoesUseCase,
            obterNegociacaoUseCase,
            obterNegociacaoClienteUseCase,
            excluirNegociacaoUseCase,
            realizarNegociacaoUseCase,
            toSimulacaoNegociacaoResourceConverter,
            toNegociacaoResourceConverter
    );

    @Test
    public void deveSimularNegociacao(){
        SimulacaoNegociacaoDTO simulacaoNegociacaoDTOMock = SimulacaoNegociacaoDTOFactory.criaSimulacaoNegociacaoDTO(1L);
        SimulacaoNegociacao simulacaoNegociacaoMock = NegociacaoFactory.criaSimulacaoNegociacao();

        when(realizarSimulacaoNegociacaoUseCase.execute(any(SimulacaoNegociacaoDTO.class))).thenReturn(simulacaoNegociacaoMock);

        SimulacaoNegociacaoResource simulacaoNegociacaoResponse = negociacaoController.simularNegociacao(simulacaoNegociacaoDTOMock);

        assertEquals(simulacaoNegociacaoMock.getCliente().getClienteId(), simulacaoNegociacaoResponse.getCliente().getClienteId());
        assertEquals(simulacaoNegociacaoMock.getCartoes().size(), simulacaoNegociacaoResponse.getCartoes().size());
        verify(realizarSimulacaoNegociacaoUseCase, atLeastOnce()).execute(any(SimulacaoNegociacaoDTO.class));
    }

    @Test
    public void deveObterTodasAsNegociacoes(){
        List<Negociacao> listaDeNegociacoesMock = NegociacaoFactory.criaListaDeNegociacao();

        when(obterTodasAsNegociacoesUseCase.execute()).thenReturn(listaDeNegociacoesMock);

        List<NegociacaoResource> listaDeNegociacoesResponse = negociacaoController.obterTodasAsNegociacoes();

        assertEquals(listaDeNegociacoesMock.size(), listaDeNegociacoesResponse.size());
        verify(obterTodasAsNegociacoesUseCase, atLeastOnce()).execute();
    }

    @Test
    public void deveObterNegociacao(){
        Negociacao negociacaoMock = NegociacaoFactory.criaNegociacao();

        when(obterNegociacaoUseCase.execute(anyLong())).thenReturn(negociacaoMock);

        NegociacaoResource negociacaoResponse = negociacaoController.obterNegociacao(1L);

        assertEquals(negociacaoMock.getNegociacaoId(), negociacaoResponse.getNegociacaoId());
        verify(obterNegociacaoUseCase, atLeastOnce()).execute(anyLong());
    }

    @Test
    public void deveObterNegociacaoPorCliente(){
        Negociacao negociacaoMock = NegociacaoFactory.criaNegociacao();

        when(obterNegociacaoClienteUseCase.execute(anyLong())).thenReturn(negociacaoMock);

        NegociacaoResource negociacaoResponse = negociacaoController.obterNegociacaoPorCliente(1L);

        assertEquals(negociacaoMock.getNegociacaoId(), negociacaoResponse.getNegociacaoId());
        verify(obterNegociacaoClienteUseCase, atLeastOnce()).execute(anyLong());
    }

    @Test
    public void deveRealizarNegociacao(){
        NegociacaoDTO negociacaoDTOMock = NegociacaoDTOFactory.criarNegociacaoDTO(1L, 1L);
        Negociacao negociacaoMock = NegociacaoFactory.criaNegociacao();

        when(realizarNegociacaoUseCase.execute(any(NegociacaoDTO.class))).thenReturn(negociacaoMock);

        NegociacaoResource negociacaoResponse = negociacaoController.realizarNegociacao(negociacaoDTOMock);

        assertEquals(negociacaoMock.getNegociacaoId(), negociacaoResponse.getNegociacaoId());
        assertEquals(negociacaoMock.getCartao().getCartaoId(), negociacaoMock.getCartao().getCartaoId());
        assertEquals(negociacaoMock.getCliente().getClienteId(), negociacaoMock.getCliente().getClienteId());
        verify(realizarNegociacaoUseCase, atLeastOnce()).execute(any(NegociacaoDTO.class));
    }

    @Test
    public void deveExcluirNegociacao(){
        doNothing().when(excluirNegociacaoUseCase).execute(anyLong());
        negociacaoController.excluirNegociacao(1L);
    }
}
