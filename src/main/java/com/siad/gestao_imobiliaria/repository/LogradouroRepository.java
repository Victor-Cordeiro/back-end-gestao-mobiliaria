package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface LogradouroRepository extends JpaRepository<Logradouro, UUID> {
    @Query("SELECT MAX(l.codigo) FROM Logradouro l")
    Long findMaxCodigo();
    Optional<Logradouro> findFirstByNomeAndTipo(String nome, TipoLogradouro tipo);
}
