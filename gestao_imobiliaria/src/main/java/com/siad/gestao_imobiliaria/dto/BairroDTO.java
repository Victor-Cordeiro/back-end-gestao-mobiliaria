package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.Cidade;
import java.util.UUID;

public record BairroDTO(Long codigoCidade, String nome, Cidade cidade) {
}
