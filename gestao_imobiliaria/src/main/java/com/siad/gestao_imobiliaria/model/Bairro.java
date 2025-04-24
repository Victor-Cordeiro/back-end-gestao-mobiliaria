package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.*;
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
@Table(name = "bairro")
public class Bairro {
    @Id
    @GeneratedValue
    private UUID id;

    private Integer codigo;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "cidade_id") // Essa é a FK para a tabela Cidade
    private Cidade cidade;
    private Boolean ativo;
    private Date createAt;
    private Date updateAt;


}
