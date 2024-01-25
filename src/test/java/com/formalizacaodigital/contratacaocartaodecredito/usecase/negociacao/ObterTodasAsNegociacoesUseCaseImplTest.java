package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.NegociacaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl.ObterTodasAsNegociacoesUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ObterTodasAsNegociacoesUseCaseImplTest {

    @Mock
    private final NegociacaoGateway negociacaoGateway = Mockito.mock(NegociacaoGateway.class);

    @InjectMocks
    private final ObterTodasAsNegociacoesUseCaseImpl obterTodasAsNegociacoesUseCase = new ObterTodasAsNegociacoesUseCaseImpl(negociacaoGateway);

    @Test
    public void deveRetornarTodasAsNegociacoes(){
        List<Negociacao> listaDeNegociacoesMock = NegociacaoFactory.criaListaDeNegociacao();

        when(negociacaoGateway.obterTodasAsNegociacoes()).thenReturn(listaDeNegociacoesMock);

        List<Negociacao> listaDeNegociacoesResponse = obterTodasAsNegociacoesUseCase.execute();

        assertEquals(listaDeNegociacoesMock.size(), listaDeNegociacoesResponse.size());
        verify(negociacaoGateway, atLeastOnce()).obterTodasAsNegociacoes();
    }

    @Test
    public void deveRetornarListaVazia(){
        List<Negociacao> listaDeNegociacoesResponse = obterTodasAsNegociacoesUseCase.execute();
        assertTrue(listaDeNegociacoesResponse.isEmpty());
    }
}
