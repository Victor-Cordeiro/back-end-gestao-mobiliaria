package com.siad.gestao_imobiliaria.exceptions;

import java.util.UUID;

public class ResponsavelLegalException extends RuntimeException {

    public ResponsavelLegalException(String message) {
        super(message);
    }

    public static ResponsavelLegalException responsavelNaoEncontrado(UUID id) {
        return new ResponsavelLegalException("Responsável não encontrado com o id: " + id);
    }

    public static ResponsavelLegalException responsavelInativo(UUID id) {
        return new ResponsavelLegalException("Responsável com o id " + id + " está inativo.");
    }
}
