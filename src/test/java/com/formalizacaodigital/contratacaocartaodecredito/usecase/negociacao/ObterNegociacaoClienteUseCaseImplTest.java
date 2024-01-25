package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.NegociacaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl.ObterNegociacaoClienteUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ObterNegociacaoClienteUseCaseImplTest {

    @Mock
    private final NegociacaoGateway negociacaoGateway = Mockito.mock(NegociacaoGateway.class);

    @InjectMocks
    private final ObterNegociacaoClienteUseCaseImpl obterNegociacaoClienteUseCase = new ObterNegociacaoClienteUseCaseImpl(negociacaoGateway);

    @Test
    public void deveObterNegociacaoCliente(){
        Negociacao negociacaoMock = NegociacaoFactory.criaNegociacao();

        when(negociacaoGateway.obterNegociacaoPorCliente(anyLong())).thenReturn(negociacaoMock);

        Negociacao negociacaoResponse = obterNegociacaoClienteUseCase.execute(1L);

        assertEquals(negociacaoMock.getNegociacaoId(), negociacaoResponse.getNegociacaoId());
        verify(negociacaoGateway, atLeastOnce()).obterNegociacaoPorCliente(anyLong());
    }

    @Test
    public void deveLancarExcecaoDeNegociacaoNaoEncontrada(){

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> obterNegociacaoClienteUseCase.execute(1L));

        assertEquals("Negociação não encontrada", exception.getStatusText());
        verify(negociacaoGateway, atLeastOnce()).obterNegociacaoPorCliente(anyLong());
    }
}
