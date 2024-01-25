package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.SimulacaoNegociacaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cliente;
import com.formalizacaodigital.contratacaocartaodecredito.domain.SimulacaoNegociacao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.SimulacaoNegociacaoDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.CartaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.ClienteFactory;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.ObterTodosOsCartoesUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.ObterClienteUseCase;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl.RealizarSimulacaoNegociacaoUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class RealizarSimulacaoNegociacaoUseCaseImplTest {

    @Mock
    private final ObterClienteUseCase obterClienteUseCase = Mockito.mock(ObterClienteUseCase.class);

    @Mock
    private final ObterTodosOsCartoesUseCase obterTodosOsCartoesUseCase = Mockito.mock(ObterTodosOsCartoesUseCase.class);

    @InjectMocks
    private final RealizarSimulacaoNegociacaoUseCaseImpl realizarSimulacaoNegociacaoUseCase = new RealizarSimulacaoNegociacaoUseCaseImpl(obterClienteUseCase, obterTodosOsCartoesUseCase);

    @Test
    public void deveRealizarSimulacao(){
        Cliente clienteMock = ClienteFactory.criaCliente();
        List<Cartao> listaDeCartoesMock = CartaoFactory.criaListaDeCartoes();
        SimulacaoNegociacaoDTO simulacaoNegociacaoDTOMock = SimulacaoNegociacaoDTOFactory.criaSimulacaoNegociacaoDTO(clienteMock.getClienteId());

        when(obterClienteUseCase.execute(anyLong())).thenReturn(clienteMock);
        when(obterTodosOsCartoesUseCase.execute()).thenReturn(listaDeCartoesMock);

        SimulacaoNegociacao simulacaoNegociacaoResponse = realizarSimulacaoNegociacaoUseCase.execute(simulacaoNegociacaoDTOMock);

        assertEquals(clienteMock.getClienteId(), simulacaoNegociacaoResponse.getCliente().getClienteId());
        assertEquals(listaDeCartoesMock.size(), simulacaoNegociacaoResponse.getCartoes().size());
    }
}
