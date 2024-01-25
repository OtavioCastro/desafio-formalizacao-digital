package com.formalizacaodigital.contratacaocartaodecredito.controller;

import com.formalizacaodigital.contratacaocartaodecredito.controller.converter.ClienteToClienteResourceConverter;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.AtualizarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.CadastrarClienteDTO;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.ClienteResource;
import com.formalizacaodigital.contratacaocartaodecredito.usecase.cliente.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final ExcluirClienteUseCase excluirClienteUseCase;
    private final ObterClienteUseCase obterClienteUseCase;
    private final ObterTodosOsClientesUseCase obterTodosOsClientesUseCase;
    private final ClienteToClienteResourceConverter toClienteResourceConverter;

    @GetMapping
    public List<ClienteResource> obterTodosOsClientes(){
        List<ClienteResource> listaDeClientes = obterTodosOsClientesUseCase.execute()
                .stream()
                .map(toClienteResourceConverter::convert)
                .toList();
        listaDeClientes.forEach(clienteMap -> clienteMap.add(linkTo(methodOn(ClienteController.class).obterCliente(clienteMap.getClienteId())).withSelfRel()));
        return listaDeClientes;
    }

    @GetMapping("/{clienteId}")
    public ClienteResource obterCliente(@PathVariable("clienteId") Long clienteId){
        ClienteResource clienteResponse = toClienteResourceConverter.convert(obterClienteUseCase.execute(clienteId));
        clienteResponse.add(linkTo(methodOn(ClienteController.class).obterTodosOsClientes()).withRel("Lista de Clientes"));
        return clienteResponse;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClienteResource cadastrarCliente(@RequestBody CadastrarClienteDTO cadastrarClienteDTO){
        return toClienteResourceConverter.convert(cadastrarClienteUseCase.execute(cadastrarClienteDTO));
    }

    @PutMapping("/{clienteId}")
    public ClienteResource atualizarCliente(@PathVariable("clienteId") Long clienteId, @RequestBody AtualizarClienteDTO atualizarClienteDTO){
        return toClienteResourceConverter.convert(atualizarClienteUseCase.execute(clienteId, atualizarClienteDTO));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{clienteId}")
    public void excluirCliente(@PathVariable("clienteId") Long clienteId){
        excluirClienteUseCase.execute(clienteId);
    }

}
