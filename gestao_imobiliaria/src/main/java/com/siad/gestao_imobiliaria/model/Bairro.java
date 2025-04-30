package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bairro")
public class Bairro {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private Long codigo;
    private String nome;
    @ManyToOne
    private Cidade cidade;
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
