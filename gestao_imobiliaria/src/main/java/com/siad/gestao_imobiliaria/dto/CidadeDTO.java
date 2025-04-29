package com.siad.gestao_imobiliaria.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class CidadeDTO {
    private UUID id;
    private Integer codigo;
    private String nome;
    private Boolean ativo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
