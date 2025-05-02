package com.siad.gestao_imobiliaria.controller;

import com.siad.gestao_imobiliaria.dto.CidadeDTO;
import com.siad.gestao_imobiliaria.model.BoletimCadastro;
import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.service.BoletimCadastroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/boletim-cadastro")
@RequiredArgsConstructor
public class BoletimCadastroController {

    private final BoletimCadastroService boletimCadastroService;


//    @GetMapping
//    public ResponseEntity<List<BoletimCadastro>> listarTodas() {
//        return ResponseEntity.ok(boletimCadastroService.);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Cidade> buscarPorId(@PathVariable UUID id) {
//        return ResponseEntity.ok(cidadeService.getCidadeById(id));
//    }
//
//    @PutMapping("delete/{id}")
//    public void deletar(@PathVariable UUID id){
//        cidadeService.deleteCidade(id);
//    }
//
//    @PostMapping
//    public ResponseEntity<Cidade> adicionar(@RequestBody CidadeDTO cidade) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.createCidade(cidade));
//    }




}
