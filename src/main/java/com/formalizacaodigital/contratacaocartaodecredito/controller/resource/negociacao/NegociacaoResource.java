package com.formalizacaodigital.contratacaocartaodecredito.controller.resource.negociacao;

import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao.CartaoResource;
import com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente.ClienteResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegociacaoResource extends RepresentationModel {
    private Long negociacaoId;
    private ClienteResource cliente;
    private CartaoResource cartao;
}
