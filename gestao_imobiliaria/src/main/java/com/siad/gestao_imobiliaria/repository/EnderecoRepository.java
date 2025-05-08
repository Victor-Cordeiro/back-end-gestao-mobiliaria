package com.siad.gestao_imobiliaria.repository;

import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.model.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    Optional<Endereco> findByCep(String cep);
    @Query("SELECT MAX(e.codigo) FROM Endereco e")
    Long findMaxCodigo();
    List<Endereco> findByAtivoTrue();
    Optional<Endereco> findByLogradouroAndBairroAndNumeroAndCep(Logradouro logradouro, Bairro bairro, String numero, String cep);
}
