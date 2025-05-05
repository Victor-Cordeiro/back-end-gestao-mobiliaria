package com.siad.gestao_imobiliaria.config;

import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.repository.CidadeRepository;
import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor

public class CidadeDataBase {

    private final CidadeRepository cidadeRepository;

    @PostConstruct
    public void init() {
        List<String> cidades = List.of(
                "Serra", "Vila Velha", "Vitória", "Linhares", "Aracruz", "Viana", "Alegre", "Pinheros", "Vargem Alta", "Pedro Canário",
                "Cariacica", "Colatina", "São Mateus", "Guarapari", "Domingos Martins", "Itapemirim", "Marataízes", "Marechal Floriano"
        );

        for (String cidade : cidades) {
            String cidadeFormatado = cidade.trim();

            boolean existe = cidadeRepository
                    .findFirstByNomeIgnoreCase(cidadeFormatado)
                    .isPresent();

            if (!existe) {
                Cidade cidadee = new Cidade();
                cidadee.setNome(cidadeFormatado);
                cidadee.setAtivo(true);
                cidadee.setCodigo(gerarProximoCodigo());
                cidadeRepository.save(cidadee);
            }
        }
    }
    public Long gerarProximoCodigo() {
        Long maior = cidadeRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }




}
