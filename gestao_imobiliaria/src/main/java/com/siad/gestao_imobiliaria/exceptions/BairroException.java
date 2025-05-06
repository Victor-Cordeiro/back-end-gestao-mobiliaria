package com.siad.gestao_imobiliaria.exceptions;

import java.util.UUID;

public class BairroException extends RuntimeException {

    public BairroException(String message) {
        super(message);
    }

    public static BairroException bairroNaoEncontrado(UUID id) {
        return new BairroException("Bairro não encontrado com o id: " + id);
    }

    public static BairroException bairroSemCidade(UUID id) {
        return new BairroException("O bairro com id " + id + " não possui uma cidade associada.");
    }

    public static BairroException cidadeInativa(UUID id) {
        return new BairroException("A cidade com id " + id + " está inativa.");
    }

    public static BairroException bairroJaExiste(String nome, String cidade) {
        return new BairroException("Bairro \"" + nome + "\" já existe na cidade \"" + cidade + "\".");
    }
}
