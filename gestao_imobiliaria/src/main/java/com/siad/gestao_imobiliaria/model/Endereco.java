package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Endereco {


    @Id
    private UUID id;
    private Logradouro lagradouro;
    private Bairro bairro;
    private String numero;
    private String complemento;
    private String cep;

}
