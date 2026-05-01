package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Logradouro {

    @Id
    @GeneratedValue
    private UUID id;
    private Long codigo;
    private String nome;
    private String nome_anterior;
    @ManyToOne
    @JoinColumn(name= "tipo_logradouro_id") // Essa é a FK para a tabela TipoLogradouro
    private TipoLogradouro tipo;
    private Boolean ativo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.ativo = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }

}
