package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.Cidade;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

public record BairroDTO(UUID id, Long codigo, String nome, Cidade cidade) {
}
