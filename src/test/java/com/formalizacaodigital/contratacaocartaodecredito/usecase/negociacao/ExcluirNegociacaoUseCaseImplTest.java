package com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.NegociacaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.NegociacaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.impl.ExcluirNegociacaoUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ExcluirNegociacaoUseCaseImplTest {

    @Mock
    private final NegociacaoGateway negociacaoGateway = Mockito.mock(NegociacaoGateway.class);

    @Mock
    private final ObterNegociacaoUseCase obterNegociacaoUseCase = Mockito.mock(ObterNegociacaoUseCase.class);

    @InjectMocks
    private final ExcluirNegociacaoUseCaseImpl excluirNegociacaoUseCase = new ExcluirNegociacaoUseCaseImpl(negociacaoGateway, obterNegociacaoUseCase);

    @Test
    public void deveExcluirNegociacao(){
        Negociacao negociacaoMock = NegociacaoFactory.criaNegociacao();

        when(obterNegociacaoUseCase.execute(anyLong())).thenReturn(negociacaoMock);
        doNothing().when(negociacaoGateway).excluirNegociacao(anyLong());

        excluirNegociacaoUseCase.execute(1L);

        verify(obterNegociacaoUseCase, atLeastOnce()).execute(anyLong());
        verify(negociacaoGateway, atLeastOnce()).excluirNegociacao(anyLong());
    }
}
