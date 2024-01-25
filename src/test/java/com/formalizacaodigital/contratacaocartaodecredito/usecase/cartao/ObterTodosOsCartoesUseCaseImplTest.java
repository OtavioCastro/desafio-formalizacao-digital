package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.CartaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.impl.ObterTodosOsCartoesUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class ObterTodosOsCartoesUseCaseImplTest {

    @Mock
    private final CartaoGateway cartaoGateway = Mockito.mock(CartaoGateway.class);

    @InjectMocks
    private final ObterTodosOsCartoesUseCaseImpl obterTodosOsCartoesUseCase = new ObterTodosOsCartoesUseCaseImpl(cartaoGateway);

    @Test
    public void deveRetornarListaDeCartao(){
        List<Cartao> cartoesMock = CartaoFactory.criaListaDeCartoes();

        when(cartaoGateway.obterTodosOsCartoes()).thenReturn(cartoesMock);

        List<Cartao> cartoesResponse = obterTodosOsCartoesUseCase.execute();

        assertFalse(cartoesResponse.isEmpty());
        assertEquals(cartoesMock.size(), cartoesResponse.size());
        verify(cartaoGateway, atLeastOnce()).obterTodosOsCartoes();
    }
}
