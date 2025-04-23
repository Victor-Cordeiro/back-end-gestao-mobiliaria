package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Cidade {

    @Id
    @GeneratedValue
    private UUID id;
    private Integer codigo;
    private String nome;



}
