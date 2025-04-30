package com.siad.gestao_imobiliaria.dto;

public record ResponsavelLegalDTO(
        Long codigo,
        String nome,
        String telefoneFixo,
        String telefoneCelular,
        String email,
        String numeroDocumento,
        String tipoResponsavel,
        EnderecoDTO enderecoResponsavel,
        Boolean ativo
) {
}
