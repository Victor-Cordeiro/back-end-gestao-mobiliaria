package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
//@Getter @Setter
@Data
@Entity
public class Cidade {

    @Id
    @GeneratedValue
    private UUID id;
    private Long codigo;
    private String nome;
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
