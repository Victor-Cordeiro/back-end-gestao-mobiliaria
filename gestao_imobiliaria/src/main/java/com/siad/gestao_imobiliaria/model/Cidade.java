package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue
    private UUID id;
    private Integer codigo;
    private String nome;
    private Boolean ativo;
    private Date createAt;
    private Date updateAt;



}
