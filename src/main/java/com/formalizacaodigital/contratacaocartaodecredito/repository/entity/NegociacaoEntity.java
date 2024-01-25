package com.formalizacaodigital.contratacaocartaodecredito.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class NegociacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long negociacaoId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cliente_cliente_id")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "cartao_cartao_id")
    private CartaoEntity cartao;

}
