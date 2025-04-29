package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TipoLogradouroRepository extends JpaRepository<TipoLogradouro, UUID> {
    // Custom query methods can be defined here if needed

    Optional<TipoLogradouro> findByNome(String nome);
}
