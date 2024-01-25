package com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CartaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.ClienteResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacaoNegociacaoResource extends RepresentationModel {
    private ClienteResource cliente;
    private List<CartaoResource> cartoes;
}
