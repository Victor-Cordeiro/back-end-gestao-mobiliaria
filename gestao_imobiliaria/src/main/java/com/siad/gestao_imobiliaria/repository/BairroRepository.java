package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BairroRepository extends JpaRepository<Bairro, UUID> {
    // Custom query methods can be defined here if needed
}
