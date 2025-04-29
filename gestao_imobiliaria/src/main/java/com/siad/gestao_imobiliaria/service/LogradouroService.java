package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogradouroService {

    private final LogradouroRepository logradouroRepository;

    public Logradouro buscarOuCriar(String nome, TipoLogradouro tipo) {
        return logradouroRepository.findByNomeAndTipo(nome, tipo)
                .orElseGet(() -> {
                    Logradouro novo = new Logradouro();
                    novo.setNome(nome);
                    novo.setTipoLogradouro(tipo);
                    return logradouroRepository.save(novo);
                });
    }

    public Logradouro buscarPorId(UUID id) {
        return logradouroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logradouro não encontrado"));
    }


    public Logradouro buscarPorNome(String nome, TipoLogradouro tipo) {
        return logradouroRepository.findByNomeAndTipo(nome, tipo)
                .orElseThrow(() -> new RuntimeException("Logradouro não encontrado"));
    }

    public Logradouro createLogradouro(String nome, TipoLogradouro tipoLogradouro) {
        Logradouro logradouro = new Logradouro();
        logradouro.setNome(nome);
        logradouro.setTipoLogradouro(tipoLogradouro);
        return logradouroRepository.save(logradouro);
    }




}
