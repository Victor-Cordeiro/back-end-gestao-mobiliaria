package com.siad.gestao_imobiliaria.controller;

import com.siad.gestao_imobiliaria.dto.LogradouroDTO;
import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import com.siad.gestao_imobiliaria.service.LogradouroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/logradouro")
@RequiredArgsConstructor
public class LogradouroController {

    private final LogradouroService logradouroService;
    private final LogradouroRepository logradouroRepository;
    private final TipoLogradouroRepository tipoLogradouroRepository;




    @GetMapping
    public ResponseEntity<List<Logradouro>> listarTodos() {
        return ResponseEntity.ok(logradouroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logradouro> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(logradouroService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Logradouro> createLogradouro(@RequestBody LogradouroDTO logradouroDATA) {
        return ResponseEntity.ok(logradouroService.createLogradouro(logradouroDATA));
    }

    @PutMapping("/deletar/{id}")
    public void deletarLogradouro(@PathVariable UUID id) {
        logradouroService.deletar(id);
    }

    @PutMapping
   public void updateLogradouro(@RequestBody LogradouroDTO logradouroDATA) {
       return ResponseEntity.ok(logradouroService.updateLogradouro(logradouroDATA));
   }





}
