package com.siad.gestao_imobiliaria.model;

import com.siad.gestao_imobiliaria.enums.TipoResponsavel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class ResponsavelLegal {

    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String telefoneFixo;
    private String telefoneCelular;
    private String email;
    private String numeroDocumento;
    private TipoResponsavel tipoResponsavel;
    private Endereco enderecoResponsavel;
    private Boolean ativo;
    private Date createAt;
    private Date updateAt;


}
