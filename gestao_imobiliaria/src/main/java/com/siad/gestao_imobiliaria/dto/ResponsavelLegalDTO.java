package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.Endereco;

public record ResponsavelLegalDTO(
        Long codigo,
        String nome,
        String telefoneFixo,
        String telefoneCelular,
        String email,
        String numeroDocumento,
        String tipoResponsavel,
        Endereco enderecoResponsavel
) {
}
