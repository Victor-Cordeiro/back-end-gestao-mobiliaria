package com.siad.gestao_imobiliaria.model;

import com.siad.gestao_imobiliaria.auditoria.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

//@Getter @Setter
@Data
@Entity
public class BoletimCadastro extends Auditable {


    @Id
    @GeneratedValue
    private UUID id;

    private Long codigo;

    private String matricula;

    @ManyToOne
    @JoinColumn(name = "responsavel_legal_id")
    private ResponsavelLegal responsavel;

    @ManyToOne
    @JoinColumn(name = "endereco_correspondencia_id")
    private Endereco enderecoCorrespondencia;


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
