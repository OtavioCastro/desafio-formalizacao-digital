package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.AtualizarCartaoDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.CartaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.impl.AtualizarCartaoUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AtualizarCartaoUseCaseImplTest {

    @Mock
    private final CartaoGateway cartaoGateway = Mockito.mock(CartaoGateway.class);
    @Mock
    private final ObterCartaoUseCase obterCartaoUseCase = Mockito.mock(ObterCartaoUseCase.class);

    @InjectMocks
    private final AtualizarCartaoUseCaseImpl atualizarCartaoUseCase = new AtualizarCartaoUseCaseImpl(cartaoGateway, obterCartaoUseCase);

    @Test
    public void deveAtualizarTaxaJurosCartao(){
        Cartao cartaoMock = CartaoFactory.criaCartao();
        Double novaTaxaJuros = 5.2;
        Cartao cartaoSalvo = CartaoFactory.criaCartaoAtualizado(cartaoMock.getAnuidadeCartao(), novaTaxaJuros);

        when(obterCartaoUseCase.execute(anyLong())).thenReturn(cartaoMock);
        when(cartaoGateway.salvarCartao(any(Cartao.class))).thenReturn(cartaoSalvo);

        Cartao cartaoResponse = atualizarCartaoUseCase.execute(1L, AtualizarCartaoDTOFactory.criaAtualizarCartaoDTO(novaTaxaJuros, null));

        assertEquals(cartaoMock.getCartaoId(), cartaoResponse.getCartaoId());
        assertEquals(cartaoMock.getAnuidadeCartao(), cartaoResponse.getAnuidadeCartao());
        assertNotEquals(cartaoMock.getTaxaJurosCartao(), cartaoResponse.getTaxaJurosCartao());
        verify(obterCartaoUseCase, atLeastOnce()).execute(anyLong());
        verify(cartaoGateway, atLeastOnce()).salvarCartao(any(Cartao.class));
    }

}
