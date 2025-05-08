package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.BoletimCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoletimCadastroRepository extends JpaRepository<BoletimCadastro, UUID> {
    List<BoletimCadastro> findByAtivoTrue();
    Optional<BoletimCadastro> findByCodigo(Long codigo);
    @Query("SELECT MAX(b.codigo) FROM BoletimCadastro b")
    Long findMaxCodigo();






}
