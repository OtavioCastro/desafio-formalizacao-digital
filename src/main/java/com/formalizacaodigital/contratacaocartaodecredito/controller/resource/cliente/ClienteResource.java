package com.formalizacaodigital.contratacaocartaodecredito.controller.resource.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResource extends RepresentationModel {
    private Long clienteId;
    private String nome;
    private String documento;
    private String telefone;
}
