package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Logradouro;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

public class EnderecoDTO {

    private UUID id;
    private Integer codigo;
    private Logradouro logradouro;
    private Bairro bairro;
    private String numero;
    private String complemento;
    private String cep;
    private Boolean ativo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
