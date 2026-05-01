package com.siad.gestao_imobiliaria.exceptions;

import java.util.UUID;

public class TipoLogradouroException extends RuntimeException {

  public TipoLogradouroException(String message) {
    super(message);
  }

  public static TipoLogradouroException tipoLogradouroNaoEncontrado(Long codigo){
    return new TipoLogradouroException("Tipo de logradouro não encontrado com o id: " + codigo);
  }
}
