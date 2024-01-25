package com.formalizacaodigital.contratacaocartaodecredito.gateway;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Cartao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.CartaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.entity.CartaoEntityFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.CartaoEntityToCartaoConverter;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.CartaoToCartaoEntityConverter;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.impl.CartaoGatewayImpl;
import com.formalizacaodigital.contratacaocartaodecredito.repository.CartaoRepository;
import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.CartaoEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartaoGatewayImplTest {

    @Mock
    private final CartaoRepository cartaoRepository = Mockito.mock(CartaoRepository.class);

    @Spy
    private final CartaoEntityToCartaoConverter toCartaoConverter = new CartaoEntityToCartaoConverter(new ModelMapper());

    @Spy
    private final CartaoToCartaoEntityConverter toCartaoEntityConverter = new CartaoToCartaoEntityConverter(new ModelMapper());

    @InjectMocks
    private final CartaoGatewayImpl cartaoGateway = new CartaoGatewayImpl(cartaoRepository, toCartaoConverter, toCartaoEntityConverter);

    @Test
    public void deveObterTodosOsCartoesEntity(){
        List<CartaoEntity> listaDeCartoesEntityMock = CartaoEntityFactory.criaListaDeCartoesEntity();

        when(cartaoRepository.findAll()).thenReturn(listaDeCartoesEntityMock);

        List<Cartao> listaDeCartoesResponse = cartaoGateway.obterTodosOsCartoes();

        assertEquals(listaDeCartoesEntityMock.size(), listaDeCartoesResponse.size());
        verify(cartaoRepository, atLeastOnce()).findAll();
    }

    @Test
    public void deveRetornarListaVaziaDeCartoesEntity(){
        List<Cartao> listaDeCartoesResponse = cartaoGateway.obterTodosOsCartoes();
        assertTrue(listaDeCartoesResponse.isEmpty());
    }

    @Test
    public void deveObterCartaoEntity(){
        CartaoEntity cartaoEntityMock = CartaoEntityFactory.criaCartao();

        when(cartaoRepository.findById(anyLong())).thenReturn(Optional.of(cartaoEntityMock));

        Cartao cartaoResponse = cartaoGateway.obterCartao(1L);

        assertEquals(cartaoEntityMock.getCartaoId(), cartaoResponse.getCartaoId());
        verify(cartaoRepository, atLeastOnce()).findById(anyLong());
    }

    @Test
    public void deveRetornarCartaoVazio(){
        Cartao cartaoResponse = cartaoGateway.obterCartao(1L);
        assertNull(cartaoResponse.getCartaoId());
    }

    @Test
    public void deveSalvarCartao(){
        Cartao cartaoMock = CartaoFactory.criaCartaoSemId();
        CartaoEntity cartaoSalvo = CartaoEntityFactory.criaCartao();

        when(cartaoRepository.save(any(CartaoEntity.class))).thenReturn(cartaoSalvo);

        Cartao cartaoResponse = cartaoGateway.salvarCartao(cartaoMock);

        assertEquals(cartaoSalvo.getCartaoId(), cartaoResponse.getCartaoId());
        verify(cartaoRepository, atLeastOnce()).save(any(CartaoEntity.class));
    }

    @Test
    public void deveExcluirCartao(){

        doNothing().when(cartaoRepository).deleteById(anyLong());

        cartaoGateway.excluirCartao(1L);

        verify(cartaoRepository, atLeastOnce()).deleteById(anyLong());
    }
}
