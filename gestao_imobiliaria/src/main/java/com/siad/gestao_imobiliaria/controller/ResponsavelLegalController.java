package com.siad.gestao_imobiliaria.controller;

import com.siad.gestao_imobiliaria.dto.ResponsavelLegalDTO;
import com.siad.gestao_imobiliaria.model.ResponsavelLegal;
import com.siad.gestao_imobiliaria.service.ResponsavelLegalService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/responsavel-legal")
@AllArgsConstructor
public class ResponsavelLegalController {

    private final ResponsavelLegalService responsavelLegalService;

    @PostMapping("/create")
    public ResponseEntity<ResponsavelLegal> createResponsavel(@RequestBody ResponsavelLegalDTO dto) {
        ResponsavelLegal responsavel = responsavelLegalService.createResponsavelLegal(dto);
        return ResponseEntity.ok(responsavel);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<ResponsavelLegal>> listarTodos() {
        return ResponseEntity.ok(responsavelLegalService.getAllResponsaveis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelLegal> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(responsavelLegalService.getById(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ResponsavelLegal> atualizar(@PathVariable UUID id, @RequestBody ResponsavelLegal responsavelLegal) {
        ResponsavelLegal atualizado = responsavelLegalService.atualizar(id, responsavelLegal);
        return ResponseEntity.ok(atualizado);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        responsavelLegalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

