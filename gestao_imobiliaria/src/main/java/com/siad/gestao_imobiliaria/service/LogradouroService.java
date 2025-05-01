package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.LogradouroDTO;
import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogradouroService {

    private final LogradouroRepository logradouroRepository;

    public Logradouro buscarPorId(UUID id) {
        return logradouroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logradouro não encontrado"));
    }

    public Logradouro createLogradouro(LogradouroDTO logradouroDATA) {
        Logradouro logradouro = new Logradouro();
        if (logradouro.getCodigo() == null) {
            Long codigo = gerarProximoCodigoSimples();
            logradouro.setCodigo(codigo);
        }
        logradouro.setNome(logradouroDATA.nome());
        //logradouro.setNome_anterior(logradouroDATA.nomeAnterior());

        //logradouro.setNome(nome);
        logradouro.setTipo(tipoLogradouro);
        return logradouroRepository.save(logradouro);
    }


    public Logradouro atualizar(UUID id, Logradouro novo) {
        Logradouro atual = logradouroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logradouro não encontrado"));

        atual.setNome_anterior(atual.getNome());
        atual.setNome(novo.getNome());
        atual.setTipo(novo.getTipo());

        return logradouroRepository.save(atual);
    }



    private Logradouro getLogradouroById(UUID id) {
        return logradouroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logradouro não encontrado"));
    }


    public void deletar(UUID id) {
        Logradouro logradouro = getLogradouroById(id);
        logradouro.setAtivo(false);
    }

    public Long gerarProximoCodigoSimples() {
        Long maior = logradouroRepository.findMaxCodigo(); // Supondo que você tenha esse método
        return (maior == null) ? 1L : maior + 1;
    }





}
