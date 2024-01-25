package com.formalizacaodigital.contratacaocartaodecredito.repository;

import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.NegociacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NegociacaoRepository extends JpaRepository<NegociacaoEntity, Long> {
    @Query("SELECT n FROM NegociacaoEntity n WHERE n.cliente.clienteId = :clienteId")
    Optional<NegociacaoEntity> findByClienteId(@Param("clienteId") Long clienteId);
}
