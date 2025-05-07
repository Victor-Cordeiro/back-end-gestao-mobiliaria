package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Endereco {


    @Id
    @GeneratedValue
    private UUID id;
    private Long codigo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "logradouro_id")
    private Logradouro logradouro;


    @ManyToOne
    @JoinColumn(name = "bairro_id")
    private Bairro bairro;
    private String numero;
    private String complemento;
    private String cep;
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
