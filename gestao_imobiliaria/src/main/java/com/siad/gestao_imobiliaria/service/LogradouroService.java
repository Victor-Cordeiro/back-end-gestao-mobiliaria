package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.LogradouroDTO;
import com.siad.gestao_imobiliaria.exceptions.LogradouroException;
import com.siad.gestao_imobiliaria.exceptions.TipoLogradouroException;
import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@AllArgsConstructor
public class LogradouroService {

    private final LogradouroRepository logradouroRepository;
    private final TipoLogradouroRepository tipoLogradouroRepository;

    public Logradouro buscarPorId(UUID id) {
        return logradouroRepository.findById(id)
                .orElseThrow(() -> LogradouroException.logradouroNaoEncontrado(id));
    }

    public Logradouro createLogradouro(LogradouroDTO logradouroDATA) {
        Logradouro logradouro = new Logradouro();

        TipoLogradouro tipoLogradouro = tipoLogradouroRepository.findByCodigo(logradouroDATA.tipoLogradouroCodigo())
                .orElseThrow(() -> TipoLogradouroException.tipoLogradouroNaoEncontrado(logradouroDATA.tipoLogradouroCodigo()));



        if (logradouroDATA.codigo() == null) {
            Long codigo = gerarProximoCodigo();
            logradouro.setCodigo(codigo);
        } else {
            logradouro.setCodigo(logradouroDATA.codigo());
        }

        logradouro.setNome(logradouroDATA.nome());
        logradouro.setNome_anterior(logradouroDATA.nome());
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
        logradouroRepository.save(logradouro);
    }

    public Long gerarProximoCodigo() {
        Long maior = logradouroRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }





}
