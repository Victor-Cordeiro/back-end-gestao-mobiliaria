package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.BoletimCadastroDTO;
import com.siad.gestao_imobiliaria.model.BoletimCadastro;
import com.siad.gestao_imobiliaria.repository.BoletimCadastroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoletimCadastroService {

    private final BoletimCadastroRepository boletimRepository;

    public BoletimCadastro createBoletimCadastro(BoletimCadastroDTO boletimCadastroDATA) {
        BoletimCadastro boletim = new BoletimCadastro();
        if (boletim.getCodigo() == null) {
            Long codigo = gerarProximoCodigo();
            boletim.setCodigo(codigo);
        }

        boletim.setMatricula(boletimCadastroDATA.matricula());
        boletim.setResponsavel(boletimCadastroDATA.responsavel());
        boletim.setEnderecoCorrespondencia(boletimCadastroDATA.enderecoCorrespondencia());
        boletim.setEnderecoImovel(boletimCadastroDATA.enderecoImovel());
        return boletimRepository.save(boletim);
    }













    public Long gerarProximoCodigo() {
        Long maior = boletimRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }


}
