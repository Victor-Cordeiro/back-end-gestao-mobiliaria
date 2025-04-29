package com.siad.gestao_imobiliaria.controller;

import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.service.TipoLogradouroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipos-logradouro")
@RequiredArgsConstructor
public class TipoLogradouroController {

    private final TipoLogradouroService tipoLogradouroService;

    @GetMapping
    public ResponseEntity<List<TipoLogradouro>> listarTodos() {
        return ResponseEntity.ok(tipoLogradouroService.getAllTipoLogradouros());
    }
}

