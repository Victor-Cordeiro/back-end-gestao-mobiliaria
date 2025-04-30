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
        // Lista de tipos de logradouro a serem inseridos, se não existirem
        List<String> tipos = List.of(
                "Rua", "Avenida", "Travessa", "Alameda", "Estrada", "Rodovia", "Viela", "Largo", "Praça", "Via",
                "Beco", "Passarela", "Passagem", "Parque", "Complexo", "Condomínio", "Setor", "Quadra", "Área",
                "Módulo", "Estação"
        );

        for (String tipo : tipos) {
            String tipoFormatado = tipo.trim();

            // Verifica se o tipo já existe no banco de dados (ignorando maiúsculas/minúsculas)
            boolean existe = tipoLogradouroRepository
                    .findByDescricaoIgnoreCase(tipoFormatado)
                    .isPresent();

            // Se não existir, cria um novo tipo de logradouro
            if (!existe) {
                TipoLogradouro tipoLogradouro = new TipoLogradouro();
                tipoLogradouro.setDescricao(tipoFormatado);
                tipoLogradouro.setAtivo(true);
                tipoLogradouro.setCodigo(geraCodigo()); // Definindo um código único, caso necessário
                tipoLogradouro.setCreateAt(LocalDateTime.now());
                tipoLogradouro.setUpdateAt(LocalDateTime.now());

                tipoLogradouroRepository.save(tipoLogradouro);
            }
        }
    }



}
