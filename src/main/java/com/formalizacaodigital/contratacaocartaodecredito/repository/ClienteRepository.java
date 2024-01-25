package com.formalizacaodigital.contratacaocartaodecredito.repository;

import com.formalizacaodigital.contratacaocartaodecredito.repository.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
