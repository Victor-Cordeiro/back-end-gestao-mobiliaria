package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.Endereco;
import jakarta.validation.constraints.NotBlank;

public record ResponsavelLegalDTO(
        Long codigo,
        String nome,
        String telefoneFixo,
        String telefoneCelular,
        String email,
        @NotBlank(message = "O número do documento (CPF/CNPJ) é obrigatório")
        String numeroDocumento,
        String tipoResponsavel,
        Endereco enderecoResponsavel
) {
}
