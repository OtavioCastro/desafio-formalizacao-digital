package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.CartaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.impl.ObterCartaoUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ObterCartaoUseCaseImplTest {

    @Mock
    private final CartaoGateway cartaoGateway = Mockito.mock(CartaoGateway.class);

    @InjectMocks
    private final ObterCartaoUseCaseImpl obterCartaoUseCase = new ObterCartaoUseCaseImpl(cartaoGateway);

    @Test
    public void deveObterCartao(){
        Cartao cartaoMock = CartaoFactory.criaCartao();

        when(cartaoGateway.obterCartao(anyLong())).thenReturn(cartaoMock);

        Cartao cartaoResponse = obterCartaoUseCase.execute(1L);

        assertEquals(cartaoMock.getCartaoId(), cartaoResponse.getCartaoId());
        verify(cartaoGateway, atLeastOnce()).obterCartao(anyLong());
    }

    @Test
    public void deveLancarExcecaoDeCartaoNaoEncontrado(){

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> obterCartaoUseCase.execute(1L));

        assertEquals("Cartão não encontrado", exception.getStatusText());
        verify(cartaoGateway, atLeastOnce()).obterCartao(anyLong());
    }
}
