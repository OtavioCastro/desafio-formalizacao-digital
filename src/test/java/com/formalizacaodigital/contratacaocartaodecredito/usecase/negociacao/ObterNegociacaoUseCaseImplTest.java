package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.NegociacaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl.ObterNegociacaoUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ObterNegociacaoUseCaseImplTest {

    @Mock
    private final NegociacaoGateway negociacaoGateway = Mockito.mock(NegociacaoGateway.class);

    @InjectMocks
    private final ObterNegociacaoUseCaseImpl obterNegociacaoUseCase = new ObterNegociacaoUseCaseImpl(negociacaoGateway);

    @Test
    public void deveObterNegociacao(){
        Negociacao negociacaoMock = NegociacaoFactory.criaNegociacao();

        when(negociacaoGateway.obterNegociacao(anyLong())).thenReturn(negociacaoMock);

        Negociacao negociacaoResponse = obterNegociacaoUseCase.execute(1L);

        assertEquals(negociacaoMock.getNegociacaoId(), negociacaoResponse.getNegociacaoId());
        verify(negociacaoGateway, atLeastOnce()).obterNegociacao(anyLong());
    }

    @Test
    public void deveLancarExcecaoDeNegociacaoNaoEncontrada(){

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> obterNegociacaoUseCase.execute(1L));

        assertEquals("Negociação não encontrada", exception.getStatusText());
        verify(negociacaoGateway, atLeastOnce()).obterNegociacao(anyLong());
    }
}
