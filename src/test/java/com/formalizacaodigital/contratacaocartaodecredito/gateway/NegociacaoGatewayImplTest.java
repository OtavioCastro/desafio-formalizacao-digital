package com.formalizacaodigital.contratacaocartaodecredito.gateway;

import com.formalizacaodigital.contratacaocartaodecredito.domain.Negociacao;
import com.formalizacaodigital.contratacaocartaodecredito.factories.domain.NegociacaoFactory;
import com.formalizacaodigital.contratacaocartaodecredito.factories.entity.NegociacaoEntityFactory;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.NegociacaoEntityToNegociacaoConverter;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.converter.NegociacaoToNegociacaoEntityConverter;
import com.formalizacaodigital.contratacaocartaodecredito.gateway.impl.NegociacaoGatewayImpl;
import com.formalizacaodigital.contratacaocartaodecredito.repository.NegociacaoRepository;
import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.NegociacaoEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class NegociacaoGatewayImplTest {

    @Mock
    private final NegociacaoRepository negociacaoRepository = Mockito.mock(NegociacaoRepository.class);

    @Spy
    private final NegociacaoEntityToNegociacaoConverter toNegociacaoConverter = new NegociacaoEntityToNegociacaoConverter(new ModelMapper());

    @Spy
    private final NegociacaoToNegociacaoEntityConverter toNegociacaoEntityConverter = new NegociacaoToNegociacaoEntityConverter(new ModelMapper());

    @InjectMocks
    private final NegociacaoGatewayImpl negociacaoGateway = new NegociacaoGatewayImpl(negociacaoRepository, toNegociacaoConverter, toNegociacaoEntityConverter);

    @Test
    public void deveObterTodasAsNegociacoes(){
        List<NegociacaoEntity> listaDeNegociacaoEntityMock = NegociacaoEntityFactory.criaListaDeNegociacao();

        when(negociacaoRepository.findAll()).thenReturn(listaDeNegociacaoEntityMock);

        List<Negociacao> listaDeNegociacao = negociacaoGateway.obterTodasAsNegociacoes();

        assertEquals(listaDeNegociacaoEntityMock.size(), listaDeNegociacao.size());
        verify(negociacaoRepository, atLeastOnce()).findAll();
    }

    @Test
    public void deveObterNegociacao(){
        NegociacaoEntity negociacaoEntityMock = NegociacaoEntityFactory.criaNegociacao();

        when(negociacaoRepository.findById(anyLong())).thenReturn(Optional.of(negociacaoEntityMock));

        Negociacao negociacaoResponse = negociacaoGateway.obterNegociacao(1L);

        assertEquals(negociacaoEntityMock.getNegociacaoId(), negociacaoResponse.getNegociacaoId());
        verify(negociacaoRepository, atLeastOnce()).findById(anyLong());
    }

    @Test
    public void deveRetornarNegociacaoVazia(){
        Negociacao negociacaoResponse = negociacaoGateway.obterNegociacao(1L);
        assertNull(negociacaoResponse.getNegociacaoId());
    }

    @Test
    public void deveObterNegociacaoPorCliente(){
        NegociacaoEntity negociacaoEntityMock = NegociacaoEntityFactory.criaNegociacao();

        when(negociacaoRepository.findByClienteId(anyLong())).thenReturn(Optional.of(negociacaoEntityMock));

        Negociacao negociacaoResponse = negociacaoGateway.obterNegociacaoPorCliente(1L);

        assertEquals(negociacaoEntityMock.getNegociacaoId(), negociacaoResponse.getNegociacaoId());
        verify(negociacaoRepository, atLeastOnce()).findByClienteId(anyLong());
    }

    @Test
    public void deveRetornarNegociacaoPorClienteVazia(){
        Negociacao negociacaoResponse = negociacaoGateway.obterNegociacaoPorCliente(1L);
        assertNull(negociacaoResponse.getNegociacaoId());
    }

    @Test
    public void deveRealizarNegociacao(){
        Negociacao negociacaoMock = NegociacaoFactory.criaNegociacaoSemId();
        NegociacaoEntity negociacaoSalva = NegociacaoEntityFactory.criaNegociacao();

        when(negociacaoRepository.save(any(NegociacaoEntity.class))).thenReturn(negociacaoSalva);

        Negociacao negociacaoResponse = negociacaoGateway.realizarNegociacao(negociacaoMock);

        assertEquals(negociacaoSalva.getNegociacaoId(), negociacaoResponse.getNegociacaoId());
        verify(negociacaoRepository, atLeastOnce()).save(any(NegociacaoEntity.class));
    }

    @Test
    public void deveExcluirNegociacao(){
        doNothing().when(negociacaoRepository).deleteById(anyLong());
        negociacaoGateway.excluirNegociacao(1L);
    }
}
