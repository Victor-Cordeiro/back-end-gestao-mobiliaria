package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogradouroRepository extends JpaRepository<Logradouro, UUID> {
    // Custom query methods can be defined here if needed
}
