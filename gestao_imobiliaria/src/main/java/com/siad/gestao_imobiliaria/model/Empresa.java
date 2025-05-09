package com.siad.gestao_imobiliaria.model;


import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Empresa {

    private String nome;
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String telefone;
    private String email;
    private Boolean ativo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    @PrePersist
    public void prePersist() {
        this.ativo = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.ativo = true;
    }
}
