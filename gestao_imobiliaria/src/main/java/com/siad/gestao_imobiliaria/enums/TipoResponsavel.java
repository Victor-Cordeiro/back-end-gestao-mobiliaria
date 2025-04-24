package com.siad.gestao_imobiliaria.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoResponsavel {
    PESSOAFISICA(1),
    PESSOAJURIDICA(2);

    private final int codigo;
}
