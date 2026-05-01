package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.CidadeDTO;
import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;


    public Cidade createCidade(CidadeDTO cidadeDTO) {
        String nome = cidadeDTO.nome().trim();

        boolean jaExiste = cidadeRepository.existsByNomeIgnoreCase(nome);
        if (jaExiste) {
            throw new IllegalArgumentException("Já existe uma cidade com esse nome: " + nome);
        }

        Cidade cidade = new Cidade();
        if (cidade.getCodigo() == null) {
            Long codigo = gerarProximoCodigo();
            cidade.setCodigo(codigo);
        }
        cidade.setNome(nome);
        return cidadeRepository.save(cidade);
    }

    public Cidade getCidadeById(UUID id) {
        return cidadeRepository.findById(id).orElse(null);
    }

    public void deleteCidade(UUID id) {
        Cidade cidade = getCidadeById(id);
        cidade.setAtivo(false);
        cidadeRepository.save(cidade);
    }

    public List<Cidade>getAllCidades() {

        return cidadeRepository.findByAtivoTrue();
    }

    public Long gerarProximoCodigo() {
        Long maior = cidadeRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }






}
