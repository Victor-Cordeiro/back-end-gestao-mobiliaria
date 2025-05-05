package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BairroRepository extends JpaRepository<Bairro, UUID> {
    Optional<Bairro> findByNomeAndCidade(String nome, Cidade cidade);
    @Query("SELECT MAX(b.codigo) FROM Bairro b")
    Long findMaxCodigo();


    List<Bairro> findByAtivoTrue();

    List<Bairro> findByCidadeIsNotNullAndCidadeAtivoTrue();







}
