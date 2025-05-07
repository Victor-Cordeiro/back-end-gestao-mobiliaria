package com.siad.gestao_imobiliaria.controller;


import com.siad.gestao_imobiliaria.dto.EnderecoDTO;
import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("/create")
    public ResponseEntity<Endereco> createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        Endereco novo = enderecoService.createEndereco(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Endereco> atualizar(@PathVariable UUID id, @RequestBody EnderecoDTO enderecoDTO) {
//        Endereco atualizado = enderecoService.atualizar(id, enderecoDTO);
//        return ResponseEntity.ok(atualizado);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(enderecoService.buscarPorId(id));
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<Endereco> buscarPorCep(@PathVariable String cep) {
        return ResponseEntity.ok(enderecoService.buscarPorCep(cep));
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        return ResponseEntity.ok(enderecoService.listarTodos());
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }



}


