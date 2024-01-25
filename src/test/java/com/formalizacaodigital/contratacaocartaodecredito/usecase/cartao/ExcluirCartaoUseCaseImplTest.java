package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.CartaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.impl.ExcluirCartaoUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ExcluirCartaoUseCaseImplTest {

    @Mock
    private final CartaoGateway cartaoGateway = Mockito.mock(CartaoGateway.class);
    @Mock
    private final ObterCartaoUseCase obterCartaoUseCase = Mockito.mock(ObterCartaoUseCase.class);

    @InjectMocks
    private final ExcluirCartaoUseCaseImpl excluirCartaoUseCase = new ExcluirCartaoUseCaseImpl(cartaoGateway, obterCartaoUseCase);

    @Test
    public void deveExcluirCartao(){
        Cartao cartaoMock = CartaoFactory.criaCartao();

        when(obterCartaoUseCase.execute(anyLong())).thenReturn(cartaoMock);
        doNothing().when(cartaoGateway).excluirCartao(anyLong());

        excluirCartaoUseCase.execute(1L);

        verify(obterCartaoUseCase, atLeastOnce()).execute(anyLong());
        verify(cartaoGateway, atLeastOnce()).excluirCartao(anyLong());
    }

}
