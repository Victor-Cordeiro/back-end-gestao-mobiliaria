package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.LogradouroDTO;
import com.siad.gestao_imobiliaria.exceptions.LogradouroException;
import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LogradouroService {

    private final LogradouroRepository logradouroRepository;
    private final TipoLogradouroRepository tipoLogradouroRepository;



    public Logradouro createLogradouro(LogradouroDTO logradouroDTO) {
        Logradouro logradouro = new Logradouro();


        TipoLogradouro tipoLogradouro = tipoLogradouroRepository.findByCodigo(logradouroDTO.tipo().getCodigo())
                .orElseThrow(() -> LogradouroException.lograoduroCodigoNaoEncontrado(logradouroDTO.tipo().getCodigo()));

        logradouro.setCodigo(logradouroDTO.codigo() != null ? logradouroDTO.codigo() : gerarProximoCodigo());
        logradouro.setNome(logradouroDTO.nome());
        logradouro.setNome_anterior(logradouroDTO.nome());
        logradouro.setTipo(tipoLogradouro);

        return logradouroRepository.save(logradouro);
    }




    public Logradouro atualizar(UUID id, Logradouro logradouroNOVO) {
        Logradouro atual = logradouroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logradouro não encontrado"));

        atual.setNome_anterior(atual.getNome());
        atual.setNome(logradouroNOVO.getNome());
        atual.setTipo(logradouroNOVO.getTipo());

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
    public Logradouro buscarPorId(UUID id) {
        return logradouroRepository.findById(id)
                .orElseThrow(() -> LogradouroException.logradouroNaoEncontrado(id));
    }

    public Long gerarProximoCodigo() {
        Long maior = logradouroRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }





}
