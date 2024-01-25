package com.formalizacaodigital.contratacaocartaodecredito.domain;

import lombok.*;

@Data
@Getter
@Setter
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Long clienteId;
    private String nome;
    private String documento;
    private String telefone;
}
