package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {


}
