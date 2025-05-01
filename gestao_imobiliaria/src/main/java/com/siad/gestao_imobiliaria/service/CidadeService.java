package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.CidadeDTO;
import com.siad.gestao_imobiliaria.model.Cidade;
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

    public Cidade createCidade(CidadeDTO cidadeDATA) {
        Cidade cidade = new Cidade();
        if (cidade.getCodigo() == null) {
            Long codigo = gerarProximoCodigoSimples();
            cidade.setCodigo(codigo);
        }
        cidade.setNome(cidadeDATA.nome());
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
