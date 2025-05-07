package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.model.ResponsavelLegal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResponsavelLegalRepository extends JpaRepository<ResponsavelLegal, UUID> {
    // Custom query methods can be defined here if needed
    @Query("SELECT MAX(r.codigo) FROM ResponsavelLegal r")
    Long findMaxCodigo();

    List<ResponsavelLegal> findByAtivoTrue();


    boolean existsByNumeroDocumento(String s);







}
