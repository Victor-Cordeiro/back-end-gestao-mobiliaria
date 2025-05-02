package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.dto.BairroDTO;
import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.repository.BairroRepository;
import com.siad.gestao_imobiliaria.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BairroService {

    private final BairroRepository bairroRepository;
    private final CidadeRepository cidadeRepository;

    public Bairro createBairro(BairroDTO bairroDATA) {
        Bairro bairro = new Bairro();
        Cidade cidade = cidadeRepository.findByCodigo(bairroDATA.codigoCidade())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada com ID: " + bairroDATA.codigoCidade()));

        if (bairro.getCodigo() == null) {
            Long codigo = gerarProximoCodigo();
            bairro.setCodigo(codigo);
        }
        bairro.setNome(bairroDATA.nome());

        bairro.setCidade(cidade);
        return bairroRepository.save(bairro);
    }

    public Bairro buscarPorNome(Bairro bairro) {
        return bairroRepository.findByNomeAndCidade(bairro.getNome(), bairro.getCidade()).orElseThrow(() -> new RuntimeException("Bairro não encontrado"));
    }

    public List<Bairro> getAllBairros() {


        return bairroRepository.findAll();
    }

    public Bairro buscarBairroById(UUID id) {
        return bairroRepository.findById(id).orElseThrow(() -> new RuntimeException("Bairro não encontrado"));
    }


    public void deleteBairro(UUID id) {
        Bairro bairro = buscarBairroById(id);
        bairro.setAtivo(false);
        bairroRepository.save(bairro);
    }

    public Long gerarProximoCodigo() {
        Long maior = bairroRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }





}
