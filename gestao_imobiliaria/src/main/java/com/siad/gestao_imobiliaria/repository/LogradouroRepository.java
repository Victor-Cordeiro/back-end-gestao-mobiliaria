package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LogradouroRepository extends JpaRepository<Logradouro, UUID> {


}
