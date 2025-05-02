package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.model.ResponsavelLegal;

import java.util.UUID;

public record BoletimCadastroDTO(UUID id, Long codigo,
                                 String matricula,
                                 ResponsavelLegal responsavel,
                                 Endereco enderecoCorrespondencia,
                                 Endereco enderecoImovel,Boolean ativo
                                 ) {
}
