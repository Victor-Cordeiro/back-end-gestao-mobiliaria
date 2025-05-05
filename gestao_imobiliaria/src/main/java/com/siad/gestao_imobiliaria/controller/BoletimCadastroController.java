package com.siad.gestao_imobiliaria.controller;

import com.siad.gestao_imobiliaria.dto.BoletimCadastroDTO;
import com.siad.gestao_imobiliaria.dto.CidadeDTO;
import com.siad.gestao_imobiliaria.model.BoletimCadastro;
import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.service.BoletimCadastroService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/boletim-cadastro")
@AllArgsConstructor
public class BoletimCadastroController {

    private final BoletimCadastroService boletimCadastroService;


    @GetMapping
    public ResponseEntity<List<BoletimCadastro>> listarTodas() {
        return ResponseEntity.ok(boletimCadastroService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletimCadastro> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(boletimCadastroService.getById(id));
    }

    @PutMapping("delete/{id}")
    public void deletar(@PathVariable UUID id){
        boletimCadastroService.delete(id);
    }

    @PostMapping
    public ResponseEntity<BoletimCadastro> adicionar(@RequestBody BoletimCadastroDTO boletimDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boletimCadastroService.createBoletimCadastro(boletimDTO));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<BoletimCadastro> atualizar(@PathVariable UUID id, @RequestBody BoletimCadastroDTO boletimDTO) {
        return ResponseEntity.ok(boletimCadastroService.update(boletimDTO, id));
    }




}
