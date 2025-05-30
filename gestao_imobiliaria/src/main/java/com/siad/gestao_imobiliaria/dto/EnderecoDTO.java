package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Logradouro;


public record EnderecoDTO(Long codigo, String numero, String complemento, String cep,
                          Logradouro logradouro, Bairro bairro) {}