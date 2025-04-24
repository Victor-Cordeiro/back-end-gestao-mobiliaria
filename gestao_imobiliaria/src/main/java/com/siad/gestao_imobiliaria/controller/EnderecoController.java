package com.siad.gestao_imobiliaria.controller;


import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {



        private final EnderecoService enderecoService;

        @PostMapping
        public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
            return ResponseEntity.ok(enderecoService.salvar(endereco));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Endereco> atualizar(@PathVariable UUID id,
                                                  @RequestBody Endereco endereco) {
            return ResponseEntity.ok(enderecoService.atualizar(id, endereco));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Endereco> buscarPorId(@PathVariable UUID id) {
            return ResponseEntity.ok(enderecoService.buscarPorId(id));
        }

        @GetMapping("/cep/{cep}")
        public ResponseEntity<Endereco> buscarPorCep(@PathVariable String cep) {
            return ResponseEntity.ok(enderecoService.buscarPorCep(cep));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable UUID id) {
            enderecoService.deletar(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping
        public ResponseEntity<List<Endereco>> listarTodos() {
            return ResponseEntity.ok(enderecoService.listarTodos());
        }
}


