package com.siad.gestao_imobiliaria.dto;

import com.siad.gestao_imobiliaria.enums.Role;

public record RegisterDTO (String login, String password, Role role){
}
