package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "logradouro")
public class Logradouro {


    @Id
    private UUID id;
    private Integer codigo;
    private String nome;
    private String nome_anterior;
    @ManyToOne
    @JoinColumn(name= "tipo_logradouro_id") // Essa é a FK para a tabela TipoLogradouro
    private TipoLogradouro tipoLogradouro;
    private Boolean ativo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;




}
