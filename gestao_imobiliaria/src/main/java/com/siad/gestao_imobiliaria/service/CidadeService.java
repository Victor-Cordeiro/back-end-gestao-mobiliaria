package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public List<Cidade>getAllCidades() {
        return cidadeRepository.findAll();
    }

    public Cidade addCidade(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public Cidade getCidadeById(UUID id) {
        return cidadeRepository.findById(id).orElse(null);
    }

    public void deleteCidade(UUID id) {
        Cidade cidade = getCidadeById(id);
        cidade.setAtivo(false);
    }


    public Long gerarProximoCodigoSimples() {
        Long maior = cidadeRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }






}
