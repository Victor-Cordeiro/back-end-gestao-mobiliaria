package com.siad.gestao_imobiliaria.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BoletimCadastroImovel {
    private UUID id;
    private String matricula;
    private ResponsavelLegal responsavel;
    private Endereco enderecoImovel;
}
