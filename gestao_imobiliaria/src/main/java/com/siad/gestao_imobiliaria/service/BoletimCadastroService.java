package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.BoletimCadastroDTO;
import com.siad.gestao_imobiliaria.exceptions.BoletimException;
import com.siad.gestao_imobiliaria.exceptions.EnderecoException;
import com.siad.gestao_imobiliaria.exceptions.ResponsavelLegalException;
import com.siad.gestao_imobiliaria.model.BoletimCadastro;
import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.model.ResponsavelLegal;
import com.siad.gestao_imobiliaria.repository.BoletimCadastroRepository;
import com.siad.gestao_imobiliaria.repository.EnderecoRepository;
import com.siad.gestao_imobiliaria.repository.ResponsavelLegalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BoletimCadastroService {

    private final BoletimCadastroRepository boletimRepository;
    private final EnderecoRepository enderecoRepository;
    private final ResponsavelLegalRepository reposponsavelLegalRepository;

    public BoletimCadastro createBoletimCadastro(BoletimCadastroDTO boletimCadastroDATA) {
        BoletimCadastro boletim = new BoletimCadastro();

        ResponsavelLegal responsavelLegal = reposponsavelLegalRepository.findById(boletimCadastroDATA.responsavel().getId())
                .orElseThrow(() -> ResponsavelLegalException.responsavelNaoEncontrado(boletimCadastroDATA.responsavel().getId()));

        Endereco enderecoCorrespondencia = enderecoRepository.findById(boletimCadastroDATA.enderecoCorrespondencia().getId())
                .orElseThrow(() -> EnderecoException.enderecoNaoEncontrado(boletimCadastroDATA.enderecoCorrespondencia().getId()));

        Endereco enderecoImovel = enderecoRepository.findById(boletimCadastroDATA.enderecoImovel().getId())
                .orElseThrow(() -> EnderecoException.enderecoNaoEncontrado(boletimCadastroDATA.enderecoImovel().getId()));


        if (boletim.getCodigo() == null) {
            Long codigo = gerarProximoCodigo();
            boletim.setCodigo(codigo);
        }

        boletim.setMatricula(boletimCadastroDATA.matricula());
        boletim.setResponsavel(boletimCadastroDATA.responsavel());
        boletim.setEnderecoCorrespondencia(enderecoCorrespondencia);
        boletim.setEnderecoImovel(enderecoImovel);
        boletim.setResponsavel(responsavelLegal);
        return boletimRepository.save(boletim);
    }

    private final BoletimCadastroRepository boletimCadastroRepository;

    public List<BoletimCadastro> getAll() {
        return boletimCadastroRepository.findByAtivoTrue();
    }

    public BoletimCadastro getById(UUID id) {
        return boletimCadastroRepository.findById(id)
                .orElseThrow(() -> BoletimException.boletimNaoEncontrado(id));
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
        boletim.setAtivo(false);
        boletimCadastroRepository.save(boletim);
    }

    public Long gerarProximoCodigo() {
        Long maior = boletimRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }


}
