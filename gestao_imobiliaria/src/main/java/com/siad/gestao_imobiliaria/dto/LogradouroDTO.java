package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

public record LogradouroDTO(UUID id, Long codigo, String nome, TipoLogradouro tipoLogradouro) {
}