package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Endereco {


    @Id
    private UUID id;
    @ManyToOne
    private Logradouro lagradouro;
    @ManyToOne
    private Bairro bairro;
    private String numero;
    private String complemento;
    private String cep;
    private Boolean ativo;
    private Date createAt;
    private Date updateAt;

}
