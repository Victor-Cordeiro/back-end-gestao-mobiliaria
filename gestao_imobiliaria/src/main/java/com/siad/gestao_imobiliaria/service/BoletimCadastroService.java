package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.BoletimCadastroDTO;
import com.siad.gestao_imobiliaria.model.BoletimCadastro;
import com.siad.gestao_imobiliaria.repository.BoletimCadastroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BoletimCadastroService {

    private final BoletimCadastroRepository boletimRepository;

    public BoletimCadastro createBoletimCadastro(BoletimCadastroDTO boletimCadastroDATA) {
        BoletimCadastro boletim = new BoletimCadastro();


        if (boletim.getCodigo() == null) {
            Long codigo = gerarProximoCodigo();
            boletim.setCodigo(codigo);
        }

        boletim.setMatricula(boletimCadastroDATA.matricula());
        boletim.setResponsavel(boletimCadastroDATA.responsavel());
        boletim.setEnderecoCorrespondencia(boletimCadastroDATA.enderecoCorrespondencia());
        boletim.setEnderecoImovel(boletimCadastroDATA.enderecoImovel());
        return boletimRepository.save(boletim);
    }

    private final BoletimCadastroRepository boletimCadastroRepository;

    public List<BoletimCadastro> getAll() {
        return boletimCadastroRepository.findAll();
    }

    public BoletimCadastro getById(UUID id) {
        return boletimCadastroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boletim não encontrado"));
    }

    public BoletimCadastro update(BoletimCadastroDTO boletimCadastroDATA, UUID id) {
        BoletimCadastro boletim = getById(id);
        boletim.setMatricula(boletimCadastroDATA.matricula());
        boletim.setResponsavel(boletimCadastroDATA.responsavel());
        boletim.setEnderecoCorrespondencia(boletimCadastroDATA.enderecoCorrespondencia());
        boletim.setEnderecoImovel(boletimCadastroDATA.enderecoImovel());
        return boletimRepository.save(boletim);
    }

    public void delete(UUID id) {
        BoletimCadastro boletim = getById(id);
        boletim.setAtivo(false); // se usar soft delete
        boletimCadastroRepository.save(boletim);
    }

    public Long gerarProximoCodigo() {
        Long maior = boletimRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }


}
