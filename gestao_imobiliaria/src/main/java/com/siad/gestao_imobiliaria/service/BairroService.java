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

    //metodo para criar um bairro, que recebe o dto de bairro como parametro
    public Bairro createBairro(BairroDTO bairroDTO) {
        Bairro bairro = new Bairro();
        Cidade cidade = cidadeRepository.findByCodigo(bairroDTO.codigoCidade())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada com ID: " + bairroDTO.codigoCidade()));


        if (bairro.getCodigo() == null) {
            Long codigo = gerarProximoCodigo();
            bairro.setCodigo(codigo);
        }


        bairro.setNome(bairroDTO.nome());
        bairro.setCidade(cidade);
        return bairroRepository.save(bairro);
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


    public Bairro buscarOuCriar(BairroDTO bairroDTO) {
        Cidade cidade = cidadeRepository.findByCodigo(bairroDTO.codigoCidade())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada com código: " + bairroDTO.codigoCidade()));

        return bairroRepository.findByNomeAndCidade(bairroDTO.nome(), cidade)
                .orElseGet(() -> {
                    Bairro novoBairro = new Bairro();
                    novoBairro.setNome(bairroDTO.nome());
                    novoBairro.setCidade(cidade);
                    novoBairro.setAtivo(true);
                    novoBairro.setCodigo(gerarProximoCodigo());
                    return bairroRepository.save(novoBairro);
                });
    }




}
