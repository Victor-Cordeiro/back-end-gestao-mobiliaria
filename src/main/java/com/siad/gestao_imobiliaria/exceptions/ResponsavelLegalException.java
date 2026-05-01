package com.siad.gestao_imobiliaria.exceptions;

import java.util.UUID;

public class ResponsavelLegalException extends RuntimeException {

    public ResponsavelLegalException(String message) {
        super(message);
    }


    public static ResponsavelLegalException responsavelCodigoNaoEncontrado(Long codigo) {
        return new ResponsavelLegalException("Responsável com o codigo" + codigo + " não encontrado.");
    }

    public static ResponsavelLegalException responsavelInativo(UUID id) {
        return new ResponsavelLegalException("Responsável com o id " + id + " está inativo.");
    }
}
