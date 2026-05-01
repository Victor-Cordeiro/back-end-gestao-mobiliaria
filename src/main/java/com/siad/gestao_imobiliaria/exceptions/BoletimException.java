package com.siad.gestao_imobiliaria.exceptions;

import java.util.UUID;

public class BoletimException extends RuntimeException {
    public BoletimException(String message) {
        super(message);
    }


    public static BoletimException boletimNaoEncontrado(UUID id) {
        return new BoletimException("Boletim não encontrado com o id: " + id);
    }


}
