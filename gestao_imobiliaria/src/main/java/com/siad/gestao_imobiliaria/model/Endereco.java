package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class Endereco {


    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true)
    private Long codigo;
    @ManyToOne
    @JoinColumn(name = "logradouro_id") // Essa é a FK para a tabela Logradouro
    private Logradouro logradouro;
    @ManyToOne
    @JoinColumn(name = "bairro_id") // Essa é a FK para a tabela Logradouro
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
