package com.siad.gestao_imobiliaria.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CidadeDTO(UUID id, Long codigo, String nome, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
}