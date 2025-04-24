package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boletim_cadastro_imovel")
public class BoletimCadastroImovel {
    @Id
    @GeneratedValue
    private UUID id;
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "responsavel_legal_id")
    private ResponsavelLegal responsavel;
    private Endereco enderecoImovel;

    private Boolean ativo;
    private Date createAt;
    private Date updateAt;
}
