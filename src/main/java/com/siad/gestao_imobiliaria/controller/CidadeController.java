package com.siad.gestao_imobiliaria.controller;

import com.siad.gestao_imobiliaria.dto.CidadeDTO;
import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.service.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cidade")
@AllArgsConstructor
public class CidadeController {

    private final CidadeService cidadeService;

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Cidade>> listarTodas() {
        return ResponseEntity.ok(cidadeService.getAllCidades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(cidadeService.getCidadeById(id));
    }

    @PutMapping("delete/{id}")
    public void deletar(@PathVariable UUID id){
        cidadeService.deleteCidade(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Cidade> createCidade(@RequestBody CidadeDTO cidade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.createCidade(cidade));
    }

}

