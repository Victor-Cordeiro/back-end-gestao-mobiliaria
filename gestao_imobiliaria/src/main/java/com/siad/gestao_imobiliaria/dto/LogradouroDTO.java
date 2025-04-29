package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

public class LogradouroDTO {

    private UUID id;
    private Integer codigo;
    private String nome;
    private String nome_anterior;
    private TipoLogradouro tipoLogradouro;
    private Boolean ativo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
