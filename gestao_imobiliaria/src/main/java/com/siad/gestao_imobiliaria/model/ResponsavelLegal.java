package com.siad.gestao_imobiliaria.model;

import com.siad.gestao_imobiliaria.auditoria.Auditable;
import com.siad.gestao_imobiliaria.enums.Role;
import com.siad.gestao_imobiliaria.enums.TipoResponsavel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class ResponsavelLegal extends Auditable {

    @Id
    @GeneratedValue
    private UUID id;
    private Long codigo;
    private String nome;
    private String telefoneFixo;
    private String telefoneCelular;
    private String email;
    private String numeroDocumento;

    @Enumerated(EnumType.STRING)
    private Role tipoUsuario;


    @Enumerated(EnumType.STRING)
    private TipoResponsavel tipoResponsavel;

    @ManyToOne
    @JoinColumn(name = "endereco_responsavel_id")
    private Endereco enderecoResponsavel;
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
