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
@Table(name = "endereco")
public class Endereco {


    @Id
    private UUID id;
    private Integer codigo;
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
    private Date createAt;
    private Date updateAt;

}
