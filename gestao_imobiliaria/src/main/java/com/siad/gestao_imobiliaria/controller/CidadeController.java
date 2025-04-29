package com.siad.gestao_imobiliaria.controller;

import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> listarTodas() {
        return ResponseEntity.ok(cidadeService.getAllCidades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(cidadeService.getCidadeById(id));
    }

//    @PostMapping
//    public ResponseEntity<Cidade> criar(@RequestParam String nome) {
//        Cidade cidade = cidadeService.addCidade(nome);
//        return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
//    }
}

