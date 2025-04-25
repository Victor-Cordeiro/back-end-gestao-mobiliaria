package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_logradouro")
public class TipoLogradouro {

    @Id
    @GeneratedValue
    private UUID id;
    private Integer codigo;
    private String descricao;
    private Boolean ativo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;






}
