package com.formalizacaodigital.contratacaocartaodecredito.controller;

import com.formalizacaodigital.contratacaocartaodecredito.controller.converter.CartaoToCartaoResourceConverter;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.AtualizarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CadastrarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CartaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.AtualizarCartaoDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.controller.dto.CadastrarCartaoDTOFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.CartaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartaoControllerTest {

    @Mock
    private final AtualizarCartaoUseCase atualizarCartaoUseCase = Mockito.mock(AtualizarCartaoUseCase.class);
    @Mock
    private final CadastrarCartaoUseCase cadastrarCartaoUseCase = Mockito.mock(CadastrarCartaoUseCase.class);
    @Mock
    private final ExcluirCartaoUseCase excluirCartaoUseCase = Mockito.mock(ExcluirCartaoUseCase.class);
    @Mock
    private final ObterCartaoUseCase obterCartaoUseCase = Mockito.mock(ObterCartaoUseCase.class);
    @Mock
    private final ObterTodosOsCartoesUseCase obterTodosOsCartoesUseCase = Mockito.mock(ObterTodosOsCartoesUseCase.class);
    @Spy
    private final CartaoToCartaoResourceConverter toCartaoResourceConverter = new CartaoToCartaoResourceConverter(new ModelMapper());

    @InjectMocks
    private final CartaoController cartaoController = new CartaoController(
            atualizarCartaoUseCase,
            cadastrarCartaoUseCase,
            excluirCartaoUseCase,
            obterCartaoUseCase,
            obterTodosOsCartoesUseCase,
            toCartaoResourceConverter
            );

    @Test
    public void deveListarTodosOsCartoes(){
        List<Cartao> listaDeCartoesMock = CartaoFactory.criaListaDeCartoes();

        when(obterTodosOsCartoesUseCase.execute()).thenReturn(listaDeCartoesMock);

        List<CartaoResource> listaDeCartoesResponse = cartaoController.obterTodosOsCartoes();

        assertEquals(listaDeCartoesMock.size(), listaDeCartoesResponse.size());
        verify(obterTodosOsCartoesUseCase, atLeastOnce()).execute();
    }

    @Test
    public void deveObterCartao(){
        Cartao cartaoMock = CartaoFactory.criaCartao();

        when(obterCartaoUseCase.execute(anyLong())).thenReturn(cartaoMock);

        CartaoResource cartaoResponse = cartaoController.obterCartao(1L);

        assertEquals(cartaoMock.getCartaoId(), cartaoResponse.getIdCartao());
        verify(obterCartaoUseCase, atLeastOnce()).execute(anyLong());
    }

    @Test
    public void deveCadastrarCartao(){
        CadastrarCartaoDTO cadastrarCartaoDTOMock = CadastrarCartaoDTOFactory.criarCadastrarCartaoDTO("BRONZE", 2.00, 1000.00);
        Cartao cartaoMock = CartaoFactory.criaCartao();

        when(cadastrarCartaoUseCase.execute(any(CadastrarCartaoDTO.class))).thenReturn(cartaoMock);

        CartaoResource cartaoResponse = cartaoController.cadastrarCartao(cadastrarCartaoDTOMock);

        assertEquals(cartaoMock.getCartaoId(), cartaoResponse.getIdCartao());
        assertEquals(cartaoMock.getCategoriaCartao(), cartaoResponse.getCategoriaCartao());
        assertEquals(cartaoMock.getTaxaJurosCartao(), cartaoResponse.getTaxaJurosCartao());
        assertEquals(cartaoMock.getAnuidadeCartao(), cartaoResponse.getAnuidadeCartao());
        verify(cadastrarCartaoUseCase, atLeastOnce()).execute(any(CadastrarCartaoDTO.class));
    }

    @Test
    public void deveAtualizarCartao(){
        AtualizarCartaoDTO atualizarCartaoDTOMock = AtualizarCartaoDTOFactory.criaAtualizarCartaoDTO(2.8, 1500.00);
        Cartao cartaoMock = CartaoFactory.criaCartaoAtualizado(atualizarCartaoDTOMock.getAnuidadeCartao(), atualizarCartaoDTOMock.getTaxaJurosCartao());

        when(atualizarCartaoUseCase.execute(anyLong(), any(AtualizarCartaoDTO.class))).thenReturn(cartaoMock);

        CartaoResource cartaoResponse = cartaoController.atualizarCartao(1L, atualizarCartaoDTOMock);

        assertEquals(cartaoMock.getCartaoId(), cartaoResponse.getIdCartao());
        verify(atualizarCartaoUseCase, atLeastOnce()).execute(anyLong(), any(AtualizarCartaoDTO.class));
    }

    @Test
    public void deveExcluirCartao(){
        doNothing().when(excluirCartaoUseCase).execute(anyLong());

        cartaoController.excluirCartao(1L);

        verify(excluirCartaoUseCase, atLeastOnce()).execute(anyLong());
    }
}
