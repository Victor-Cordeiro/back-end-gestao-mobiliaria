package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CidadeRepository extends JpaRepository<Cidade, UUID> {

    @Query("SELECT MAX(c.codigo) FROM Cidade c")
    Long findMaxCodigo();

    Optional<Cidade> findByCodigo(Long codigo);

    List<Cidade> findByAtivoTrue();

    Optional<Cidade> findFirstByNomeIgnoreCase(String nome);


}
