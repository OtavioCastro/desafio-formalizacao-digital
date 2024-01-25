package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.NegociacaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.NegociacaoDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.CartaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.ClienteFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.NegociacaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.ObterCartaoUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.ObterClienteUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl.RealizarNegociacaoUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RealizarNegociacaoUseCaseImplTest {

    @Mock
    private final NegociacaoGateway negociacaoGateway = Mockito.mock(NegociacaoGateway.class);

    @Mock
    private final ObterClienteUseCase obterClienteUseCase = Mockito.mock(ObterClienteUseCase.class);

    @Mock
    private final ObterCartaoUseCase obterCartaoUseCase = Mockito.mock(ObterCartaoUseCase.class);

    @InjectMocks
    private final RealizarNegociacaoUseCaseImpl realizarNegociacaoUseCase = new RealizarNegociacaoUseCaseImpl(negociacaoGateway, obterClienteUseCase, obterCartaoUseCase);

    @Test
    public void deveRealizarNegociacao(){
        Cliente clienteMock = ClienteFactory.criaCliente();
        Cartao cartaoMock = CartaoFactory.criaCartao();
        NegociacaoDTO negociacaoDTOMock = NegociacaoDTOFactory.criarNegociacaoDTO(clienteMock.getClienteId(), cartaoMock.getCartaoId());
        Negociacao negociacaoMock = NegociacaoFactory.criaNegociacao();

        when(obterClienteUseCase.execute(anyLong())).thenReturn(clienteMock);
        when(obterCartaoUseCase.execute(anyLong())).thenReturn(cartaoMock);
        when(negociacaoGateway.realizarNegociacao(any(Negociacao.class))).thenReturn(negociacaoMock);

        Negociacao negociacaoResponse = realizarNegociacaoUseCase.execute(negociacaoDTOMock);

        assertEquals(negociacaoMock.getNegociacaoId(), negociacaoResponse.getNegociacaoId());
        assertEquals(negociacaoMock.getCliente().getClienteId(), negociacaoResponse.getCliente().getClienteId());
        assertEquals(negociacaoMock.getCartao().getCartaoId(), negociacaoResponse.getCartao().getCartaoId());
        verify(obterClienteUseCase, atLeastOnce()).execute(anyLong());
        verify(obterCartaoUseCase, atLeastOnce()).execute(anyLong());
        verify(negociacaoGateway, atLeastOnce()).realizarNegociacao(any(Negociacao.class));
    }
}
