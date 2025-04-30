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
public class BoletimCadastroImovel {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true)
    private Long codigo;
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "responsavel_legal_id")
    private ResponsavelLegal responsavel;

    @ManyToOne
    @JoinColumn(name = "endereco_imovel_id")
    private Endereco enderecoImovel;

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
