package com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cartao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoResource extends RepresentationModel {
    private Long idCartao;
    private String categoriaCartao;
    private Double taxaJurosCartao;
    private Double anuidadeCartao;
}
