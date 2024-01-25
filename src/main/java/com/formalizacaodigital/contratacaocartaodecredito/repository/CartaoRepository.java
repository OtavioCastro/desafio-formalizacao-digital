package com.formalizacaodigital.contratacaocartaodecredito.repository;

import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {
}
