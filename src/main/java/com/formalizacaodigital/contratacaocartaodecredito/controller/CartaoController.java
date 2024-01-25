package com.formalizacaodigital.contratacaocartaodecredito.controller;

import com.formalizacaodigital.contratacaocartaodecredito.controller.converter.CartaoToCartaoResourceConverter;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.AtualizarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CadastrarCartaoDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CartaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cartao.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
@RequestMapping("/cartao")
public class CartaoController {

    private final AtualizarCartaoUseCase atualizarCartaoUseCase;
    private final CadastrarCartaoUseCase cadastrarCartaoUseCase;
    private final ExcluirCartaoUseCase excluirCartaoUseCase;
    private final ObterCartaoUseCase obterCartaoUseCase;
    private final ObterTodosOsCartoesUseCase obterTodosOsCartoesUseCase;
    private final CartaoToCartaoResourceConverter toCartaoResourceConverter;

    @GetMapping
    public List<CartaoResource> obterTodosOsCartoes() {
        List<CartaoResource> listaDeCartoes = obterTodosOsCartoesUseCase.execute()
                .stream()
                .map(toCartaoResourceConverter::convert)
                .toList();
        listaDeCartoes.forEach(cartaoMap -> cartaoMap.add(linkTo(methodOn(CartaoController.class).obterCartao(cartaoMap.getIdCartao())).withSelfRel()));
        return listaDeCartoes;
    }

    @GetMapping("/{cartaoId}")
    public CartaoResource obterCartao(@PathVariable("cartaoId") Long cartaoId) {
        CartaoResource cartaoResponse = toCartaoResourceConverter.convert(obterCartaoUseCase.execute(cartaoId));
        cartaoResponse.add(linkTo(methodOn(CartaoController.class).obterTodosOsCartoes()).withRel("Lista de Cartões"));
        return cartaoResponse;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CartaoResource cadastrarCartao(@RequestBody CadastrarCartaoDTO cadastrarCartaoDTO) {
        CartaoResource cartaoResponse = toCartaoResourceConverter.convert(cadastrarCartaoUseCase.execute(cadastrarCartaoDTO));
        cartaoResponse.add(linkTo(methodOn(CartaoController.class).obterCartao(cartaoResponse.getIdCartao())).withSelfRel());
        return cartaoResponse;
    }

    @PutMapping("/{cartaoId}")
    public CartaoResource atualizarCartao(@PathVariable("cartaoId") Long cartaoId, @RequestBody AtualizarCartaoDTO atualizarCartaoDTO) {
        CartaoResource cartaoResponse = toCartaoResourceConverter.convert(atualizarCartaoUseCase.execute(cartaoId, atualizarCartaoDTO));
        cartaoResponse.add(linkTo(methodOn(CartaoController.class).obterCartao(cartaoResponse.getIdCartao())).withSelfRel());
        return cartaoResponse;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{cartaoId}")
    public void excluirCartao(@PathVariable("cartaoId") Long cartaoId) {
        excluirCartaoUseCase.execute(cartaoId);
    }
}
