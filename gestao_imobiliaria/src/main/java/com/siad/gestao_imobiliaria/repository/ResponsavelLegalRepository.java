package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.ResponsavelLegal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResponsavelLegalRepository extends JpaRepository<ResponsavelLegal, UUID> {
    // Custom query methods can be defined here if needed
}
