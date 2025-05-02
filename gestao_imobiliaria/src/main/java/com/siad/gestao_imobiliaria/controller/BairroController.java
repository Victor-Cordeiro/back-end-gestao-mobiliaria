package com.siad.gestao_imobiliaria.controller;


import com.siad.gestao_imobiliaria.dto.BairroDTO;
import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.service.BairroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bairros")
@RequiredArgsConstructor
public class BairroController {

    private final BairroService bairroService;


    //ok
    @GetMapping
    public ResponseEntity<List<Bairro>> listarTodas() {
        return ResponseEntity.ok(bairroService.getAllBairros());
    }

    //ok
    @GetMapping("/{id}")
    public ResponseEntity<Bairro> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(bairroService.buscarBairroById(id));
    }

    //ok
    @PostMapping
    public ResponseEntity<Bairro> adicionarBairro(@RequestBody BairroDTO bairroDATA) {
        return ResponseEntity.ok(bairroService.createBairro(bairroDATA));
    }

    //ok
    @PutMapping("delete/{id}")
    public void deletarBairro(@PathVariable UUID id) {
        bairroService.deleteBairro(id);
    }




}
