package com.siad.gestao_imobiliaria.config;

import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class TipoLogradouroDataBase {

    private final TipoLogradouroRepository tipoLogradouroRepository;

    @PostConstruct
    public void init() {
        List<String> tipos = List.of(
                "Rua", "Avenida", "Travessa", "Alameda", "Estrada", "Rodovia", "Viela", "Largo", "Praça", "Via",
                "Beco", "Passarela", "Passagem", "Parque", "Complexo", "Condomínio", "Setor", "Quadra", "Área",
                "Módulo", "Estação"
        );

        for (String tipo : tipos) {
            String tipoFormatado = tipo.trim();

            boolean existe = tipoLogradouroRepository
                    .findByDescricaoIgnoreCase(tipoFormatado)
                    .isPresent();

            if (!existe) {
                TipoLogradouro tipoLogradouro = new TipoLogradouro();
                tipoLogradouro.setDescricao(tipoFormatado);
                tipoLogradouro.setAtivo(true);
                tipoLogradouro.setCodigo(gerarProximoCodigo());
                tipoLogradouroRepository.save(tipoLogradouro);
            }
        }
    }
    public Long gerarProximoCodigo() {
        Long maior = tipoLogradouroRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }



}
