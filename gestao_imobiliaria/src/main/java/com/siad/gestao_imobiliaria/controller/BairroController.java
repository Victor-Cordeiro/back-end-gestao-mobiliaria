package com.siad.gestao_imobiliaria.controller;


import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Cidade;
import com.siad.gestao_imobiliaria.service.BairroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/bairros")
@RequiredArgsConstructor
public class BairroController {

    private final BairroService bairroService;


    @GetMapping("/{id}")
    public ResponseEntity<Bairro> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(bairroService.getBairroById(id));
    }


    @PostMapping
    public ResponseEntity<Bairro> adicionarBairro(@RequestBody Bairro bairro) {
        return ResponseEntity.ok(bairroService.addBairro(bairro));
    }

    @PutMapping("delete/{id}")
    public void deletarBairro(@PathVariable UUID id) {
        bairroService.deleteBairro(id);
    }



}
