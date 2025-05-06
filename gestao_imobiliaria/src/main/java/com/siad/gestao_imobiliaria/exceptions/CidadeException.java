package com.siad.gestao_imobiliaria.exceptions;

public class CidadeException extends RuntimeException {

  public CidadeException(String message) {
    super(message);
  }

  public static CidadeException cidadeNaoEncontrada(Long codigo) {
    return new CidadeException("Cidade não encontrada com o código: " + codigo);
  }

  public static CidadeException cidadeInativa(Long codigo) {
    return new CidadeException("Cidade com o código " + codigo + " está inativa.");
  }
}

