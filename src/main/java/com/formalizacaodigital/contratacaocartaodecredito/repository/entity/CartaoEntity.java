package com.formalizacaodigital.contratacaocartaodecredito.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartaoId;
    private String categoriaCartao;
    private Double taxaJurosCartao;
    private Double anuidadeCartao;
}
