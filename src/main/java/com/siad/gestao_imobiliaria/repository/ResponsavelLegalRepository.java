package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.ResponsavelLegal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResponsavelLegalRepository extends JpaRepository<ResponsavelLegal, UUID> {
    @Query("SELECT MAX(r.codigo) FROM ResponsavelLegal r")
    Long findMaxCodigo();
    List<ResponsavelLegal> findByAtivoTrue();
    Optional<ResponsavelLegal> findByCodigo(Long codigo);

    @Query(value = "SELECT * FROM responsavel_legal WHERE EXTRACT(DAY FROM data_nascimento) = :dia AND EXTRACT(MONTH FROM data_nascimento) = :mes", nativeQuery = true)
    List<ResponsavelLegal> buscarPorDiaEMes(@Param("dia") int dia, @Param("mes") int mes);


}
