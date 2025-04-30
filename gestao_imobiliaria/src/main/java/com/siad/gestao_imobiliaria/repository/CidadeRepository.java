package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CidadeRepository extends JpaRepository<Cidade, UUID> {




    @Query("SELECT MAX(e.codigo) FROM Endereco e")
    Long findMaxCodigo();



    // Custom query methods can be defined here if needed{
}
