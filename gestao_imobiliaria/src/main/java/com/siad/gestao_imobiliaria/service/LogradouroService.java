package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogradouroService {

    private final LogradouroRepository logradouroRepository;

    public Logradouro createLogradouro(String nome, UUID id) {
        Logradouro logradouro = new Logradouro();
        logradouro.setNome(nome);
        logradouro.setTipoLogradouro(TipoLogradouroRepository(tipoLogradouroId).orElse(null));

        return logradouroRepository.save(logradouro);
    }



}
