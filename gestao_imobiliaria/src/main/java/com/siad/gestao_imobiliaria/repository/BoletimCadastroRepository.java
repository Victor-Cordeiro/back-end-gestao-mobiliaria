package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.BoletimCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface BoletimCadastroRepository extends JpaRepository<BoletimCadastro, UUID> {


    @Query("SELECT MAX(e.codigo) FROM boletim_cadastro_imovel e")
    Long findMaxCodigo();
    // Custom query methods can be defined here if needed{
}
