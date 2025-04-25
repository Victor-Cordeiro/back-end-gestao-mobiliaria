package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.repository.BairroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BairroService {

    private final BairroRepository bairroRepository;


    public Bairro buscarOuCriar(String nome, Cidade cidade) {
        return bairroRepository.findByNomeAndCidade(nome, cidade)
                .orElseGet(() -> {
                    Bairro novo = new Bairro();
                    novo.setNome(nome);
                    novo.setCidade(cidade);
                    return bairroRepository.save(novo);

                });
    }



    public Bairro getBairroById(UUID id) {
        return bairroRepository.findById(id).orElse(null);
    }



}
