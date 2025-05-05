package com.siad.gestao_imobiliaria.controller;


import com.siad.gestao_imobiliaria.dto.BairroDTO;
import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.service.BairroService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bairro")
@AllArgsConstructor
public class BairroController {

    private final BairroService bairroService;

    //Criar um novo bairro
    @PostMapping("/adicionar")
    public ResponseEntity<Bairro> adicionarBairro(@RequestBody BairroDTO bairroDATA) {
        return ResponseEntity.ok(bairroService.createBairro(bairroDATA));
    }


    //Listar todos os bairros
    @GetMapping
    public ResponseEntity<List<Bairro>> listarTodas() {
        return ResponseEntity.ok(bairroService.getAllBairros());
    }

    //Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<Bairro> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(bairroService.buscarBairroById(id));
    }


    //deleta um bairro
    @PutMapping("delete/{id}")
    public void deletarBairro(@PathVariable UUID id) {
        bairroService.deleteBairro(id);
    }

    @PostMapping("/buscar-ou-criar")
    public ResponseEntity<Bairro> buscarOuCriar(@RequestBody BairroDTO bairroDTO) {
        Bairro bairro = bairroService.buscarOuCriar(bairroDTO);
        return ResponseEntity.ok(bairro);
    }








}
