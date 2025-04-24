package com.siad.gestao_imobiliaria.model;

import com.siad.gestao_imobiliaria.enums.TipoResponsavel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "responsavel_legal")
public class ResponsavelLegal {

    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String telefoneFixo;
    private String telefoneCelular;
    private String email;
    private String numeroDocumento;

    @Enumerated(EnumType.STRING)
    private TipoResponsavel tipoResponsavel;

    @OneToOne
    @JoinColumn(name = "endereco_responsavel_id")
    private Endereco enderecoResponsavel;
    private Boolean ativo;
    private Date createAt;
    private Date updateAt;


}
