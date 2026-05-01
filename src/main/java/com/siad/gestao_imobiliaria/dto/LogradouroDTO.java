package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.TipoLogradouro;


public record LogradouroDTO(String nome, TipoLogradouro tipo, Long codigo) {}
