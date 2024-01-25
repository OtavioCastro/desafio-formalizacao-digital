package com.formalizacaodigital.contratacaocartaodecredito.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clienteId;
    private String nome;
    private String documento;
    private String telefone;
}
