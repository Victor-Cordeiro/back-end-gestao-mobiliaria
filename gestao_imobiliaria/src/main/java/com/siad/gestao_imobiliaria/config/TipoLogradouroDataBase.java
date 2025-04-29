package com.siad.gestao_imobiliaria.config;


import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TipoLogradouroDataBase {
    private final TipoLogradouroRepository tipoLogradouroRepository;

    @PostConstruct
    public void init() {
        List<String> tipos = List.of(
                "Rua", "Avenida", "Travessa", "Alameda", "Estrada", "Rodovia", "Viela", "Largo", "Praça", "Via", "Beco", "Passarela",
                "Passagem", "Parque", "Complexo", "Condomínio", "Setor", "Quadra", "Área", "Módulo", "Estação"
        );
        for (String tipo : tipos) {
            tipoLogradouroRepository.findByNome(tipo).orElseGet(() ->
                    //tipoLogradouroRepository.save(TipoLogradouro.builder().descricao(tipo).build())
            );
        }
    }



}
