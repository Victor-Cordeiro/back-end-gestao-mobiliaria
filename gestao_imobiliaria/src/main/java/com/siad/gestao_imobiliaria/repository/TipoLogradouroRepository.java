package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TipoLogradouroRepository extends JpaRepository<TipoLogradouro, UUID> {
    Optional<Object> findByDescricaoIgnoreCase(String tipoFormatado);

    @Query("SELECT MAX(e.codigo) FROM TipoLogradouro e")
    Long findMaxCodigo();

    Optional<TipoLogradouro> findByCodigo(Long codigo);

    // Custom query methods can be defined here if needed

    List<TipoLogradouro> findByAtivoTrue();


}
