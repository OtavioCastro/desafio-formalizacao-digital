package com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CadastrarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.CadastrarCartaoDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.CartaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.CartaoGateway;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.impl.CadastrarCartaoUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CadastrarCartaoUseCaseImplTest {

    @Mock
    private final CartaoGateway cartaoGateway = Mockito.mock(CartaoGateway.class);

    @InjectMocks
    private CadastrarCartaoUseCaseImpl cadastrarCartaoUseCase = new CadastrarCartaoUseCaseImpl(cartaoGateway);

    @Test
    public void deveCadastrarOCartao(){
        Cartao cartaoMock = CartaoFactory.criaCartao();
        CadastrarCartaoDTO cadastrarCartaoDTOMock = CadastrarCartaoDTOFactory.criarCadastrarCartaoDTO("BRONZE", 2.00, 1000.00);

        when(cartaoGateway.salvarCartao(any(Cartao.class))).thenReturn(cartaoMock);

        Cartao cartaoResponse = cadastrarCartaoUseCase.execute(cadastrarCartaoDTOMock);

        assertEquals(cartaoMock.getCartaoId(), cartaoResponse.getCartaoId());
        verify(cartaoGateway, atLeastOnce()).salvarCartao(any(Cartao.class));
    }
}
