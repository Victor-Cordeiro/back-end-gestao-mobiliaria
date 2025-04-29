package com.siad.gestao_imobiliaria.controller;

import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
import com.siad.gestao_imobiliaria.service.LogradouroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/logradouros")
@RequiredArgsConstructor
public class LogradouroController {

    private final LogradouroService logradouroService;
    private final LogradouroRepository logradouroRepository;



    @GetMapping
    public ResponseEntity<List<Logradouro>> listarTodos() {
        return ResponseEntity.ok(logradouroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logradouro> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(logradouroService.buscarPorId(id));
    }


    @PostMapping("/criar")
    public ResponseEntity<Logradouro> salvar(@RequestBody Logradouro logradouro) {
        Logradouro salvo = logradouroRepository.save(logradouro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PostMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("POST recebido com sucesso!");
    }

}
