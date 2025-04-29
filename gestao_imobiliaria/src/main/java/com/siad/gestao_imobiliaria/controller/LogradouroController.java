package com.siad.gestao_imobiliaria.controller;

import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.service.LogradouroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/logradouros")
@RequiredArgsConstructor
public class LogradouroController {

    private final LogradouroService logradouroService;

    @GetMapping("/{id}")
    public ResponseEntity<Logradouro> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(logradouroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Logradouro> criar(@RequestParam String nome, @RequestParam TipoLogradouro tipologradouro) {

        Logradouro logradouro = logradouroService.createLogradouro(nome, tipologradouro);
        return ResponseEntity.status(HttpStatus.CREATED).body(logradouro);
    }
}
