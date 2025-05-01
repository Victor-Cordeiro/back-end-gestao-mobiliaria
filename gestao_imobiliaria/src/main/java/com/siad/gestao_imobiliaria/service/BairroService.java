package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.dto.BairroDTO;
import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.repository.BairroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BairroService {

    private final BairroRepository bairroRepository;

    public Bairro createBairro(BairroDTO bairroDATA) {
        Bairro bairro = new Bairro();
        if (bairro.getCodigo() == null) {
            Long codigo = gerarProximoCodigo();
            bairro.setCodigo(codigo);
        }
        bairro.setNome(bairroDATA.nome());
        bairro.setCidade(bairroDATA.cidade());
        return bairroRepository.save(bairro);
    }


    public Bairro buscarPorNome(Bairro bairro) {
        return bairroRepository.findByNomeAndCidade(bairro.getNome(), bairro.getCidade()).orElseThrow(() -> new RuntimeException("Bairro não encontrado"));
    }



    public Bairro buscarBairroById(UUID id) {
        return bairroRepository.findById(id).orElseThrow(() -> new RuntimeException("Bairro não encontrado"));
    }

    public void deleteBairro(UUID id) {
        Bairro bairro = buscarBairroById(id);
        bairro.setAtivo(false);
    }

    public Long gerarProximoCodigo() {
        Long maior = bairroRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }





}
