package com.formalizacaodigital.contratacaocartaodecredito.controller;

import com.formalizacaodigital.contratacaocartaodecredito.controller.converter.NegociacaoToNegociacaoResourceConverter;
import com.formalizacaodigital.contratacaocartaodecredito.controller.converter.SimulacaoNegociacaoToSimulacaoNegociacaoResourceConverter;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.NegociacaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.NegociacaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.SimulacaoNegociacaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao.SimulacaoNegociacaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.negociacao.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
@RequestMapping("/negociacao")
public class NegociacaoController {

    private final RealizarSimulacaoNegociacaoUseCase realizarSimulacaoNegociacaoUseCase;
    private final ObterTodasAsNegociacoesUseCase obterTodasAsNegociacoesUseCase;
    private final ObterNegociacaoUseCase obterNegociacaoUseCase;
    private final ObterNegociacaoClienteUseCase obterNegociacaoClienteUseCase;
    private final ExcluirNegociacaoUseCase excluirNegociacaoUseCase;
    private final RealizarNegociacaoUseCase realizarNegociacaoUseCase;
    private final SimulacaoNegociacaoToSimulacaoNegociacaoResourceConverter toSimulacaoNegociacaoResourceConverter;
    private final NegociacaoToNegociacaoResourceConverter toNegociacaoResourceConverter;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/simulacao")
    public SimulacaoNegociacaoResource simularNegociacao(@RequestBody SimulacaoNegociacaoDTO simulacaoNegociacaoDTO){
        return toSimulacaoNegociacaoResourceConverter.convert(realizarSimulacaoNegociacaoUseCase.execute(simulacaoNegociacaoDTO));
    }

    @GetMapping
    public List<NegociacaoResource> obterTodasAsNegociacoes(){
        List<NegociacaoResource> listaDeNegociacoes = obterTodasAsNegociacoesUseCase.execute()
                .stream()
                .map(toNegociacaoResourceConverter::convert)
                .toList();
        listaDeNegociacoes.forEach(negociacaoMap -> negociacaoMap.add(linkTo(methodOn(NegociacaoController.class).obterNegociacao(negociacaoMap.getNegociacaoId())).withSelfRel()));
        return listaDeNegociacoes;
    }

    @GetMapping("/{negociacaoId}")
    public NegociacaoResource obterNegociacao(@PathVariable("negociacaoId") Long negociacaoId){
        NegociacaoResource negociacaoResponse = toNegociacaoResourceConverter.convert(obterNegociacaoUseCase.execute(negociacaoId));
        negociacaoResponse.add(linkTo(methodOn(NegociacaoController.class).obterTodasAsNegociacoes()).withRel("Lista de Negociacoes"));
        return negociacaoResponse;
    }

    @GetMapping("/cliente/{clienteId}")
    public NegociacaoResource obterNegociacaoPorCliente(@PathVariable("clienteId") Long clienteId){
        NegociacaoResource negociacaoResponse = toNegociacaoResourceConverter.convert(obterNegociacaoClienteUseCase.execute(clienteId));
        negociacaoResponse.add(linkTo(methodOn(NegociacaoController.class).obterNegociacao(negociacaoResponse.getNegociacaoId())).withSelfRel());
        return negociacaoResponse;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public NegociacaoResource realizarNegociacao(@RequestBody NegociacaoDTO negociacaoDTO){
        NegociacaoResource negociacaoResponse = toNegociacaoResourceConverter.convert(realizarNegociacaoUseCase.execute(negociacaoDTO));
        negociacaoResponse.add(linkTo(methodOn(NegociacaoController.class).obterNegociacao(negociacaoResponse.getNegociacaoId())).withSelfRel());
        return negociacaoResponse;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{negociacaoId}")
    public void excluirNegociacao(@PathVariable("negociacaoId") Long negociacaoId){
        excluirNegociacaoUseCase.execute(negociacaoId);
    }
}
