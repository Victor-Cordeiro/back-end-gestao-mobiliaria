package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.Cidade;
import java.util.UUID;

public record BairroDTO(UUID id, Long codigo, Long codigoCidade, String nome, Cidade cidade, Boolean ativo) {
}
