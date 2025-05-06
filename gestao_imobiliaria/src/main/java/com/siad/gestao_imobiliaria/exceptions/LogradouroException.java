package com.siad.gestao_imobiliaria.exceptions;

import java.util.UUID;

public class LogradouroException extends RuntimeException {

    public LogradouroException(String message) {
        super(message);
    }

    public static LogradouroException logradouroNaoEncontrado(UUID id) {
        return new LogradouroException("Logradouro não encontrado com o id: " + id);
    }

    public static LogradouroException logradouroSemTipo(UUID id) {
        return new LogradouroException("Logradouro com o id " + id + " não possui tipo associado.");
    }
}
