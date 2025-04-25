package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoLogradouroService {

    private final TipoLogradouroRepository tipoLogradouroRepository;

    public List<TipoLogradouro> getAllTipoLogradouros() {
        return tipoLogradouroRepository.findAll();
    }





}
