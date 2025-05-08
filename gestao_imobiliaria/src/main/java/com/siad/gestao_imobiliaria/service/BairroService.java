package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.dto.BairroDTO;
import com.siad.gestao_imobiliaria.exceptions.BairroException;
import com.siad.gestao_imobiliaria.exceptions.CidadeException;
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

    public Bairro createBairro(BairroDTO bairroDTO) {
        Bairro bairro = new Bairro();
        Cidade cidade = cidadeRepository.findByCodigo(bairroDTO.cidade().getCodigo())
                .orElseThrow(() -> CidadeException.cidadeNaoEncontrada(bairroDTO.cidade().getCodigo()));

        bairroRepository.findFirstByNomeAndCidade(bairroDTO.nome(), cidade)
                .ifPresent(b -> {
                    throw BairroException.bairroJaExiste(bairroDTO.nome(), cidade.getNome());
                });

        if (bairro.getCodigo() == null) {
            Long codigo = gerarProximoCodigo();
            bairro.setCodigo(codigo);
        }
        bairro.setNome(bairroDTO.nome());
        bairro.setCidade(cidade);
        return bairroRepository.save(bairro);
    }

    public List<Bairro> getAllBairros() {
        return bairroRepository.findByAtivoTrueAndCidadeIsNotNullAndCidadeAtivoTrue();
    }

    public Bairro buscarBairroById(UUID id) {
        return bairroRepository.findById(id).orElseThrow(() -> BairroException.bairroNaoEncontrado(id));

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
