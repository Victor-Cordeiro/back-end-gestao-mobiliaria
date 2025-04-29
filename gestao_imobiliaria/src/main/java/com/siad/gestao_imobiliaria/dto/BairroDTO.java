package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.Cidade;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

public class BairroDTO {
    private UUID id;
    private Integer codigo;
    private String nome;
    private Cidade cidade;
    private Boolean ativo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
