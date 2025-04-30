package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.repository.BoletimCadastroImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoletimCadastroImovelService {

    private final BoletimCadastroImovelRepository boletimCadastroImovelRepository;



















    public Long gerarProximoCodigoSimples() {
        Long maior = boletimCadastroImovelRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }



}
