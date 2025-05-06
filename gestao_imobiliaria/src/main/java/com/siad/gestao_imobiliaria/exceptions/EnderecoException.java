package com.siad.gestao_imobiliaria.exceptions;

import java.util.UUID;

public class EnderecoException extends RuntimeException {

    public EnderecoException(String message) {
        super(message);
    }

    public static EnderecoException enderecoNaoEncontrado(UUID id) {
        return new EnderecoException("Endereço não encontrado com o id: " + id);
    }

    public static EnderecoException enderecoInvalido(String motivo) {
        return new EnderecoException("Endereço inválido: " + motivo);
    }
}
