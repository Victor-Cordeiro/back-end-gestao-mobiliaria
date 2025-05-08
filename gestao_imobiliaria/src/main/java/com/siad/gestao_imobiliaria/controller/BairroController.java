package com.siad.gestao_imobiliaria.controller;

import com.siad.gestao_imobiliaria.dto.BairroDTO;
import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.service.BairroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bairro")
@AllArgsConstructor
public class BairroController {

    private final BairroService bairroService;

    @PostMapping("/create")
    public ResponseEntity<Bairro> createBairro(@RequestBody BairroDTO bairroDATA) {
        return ResponseEntity.ok(bairroService.createBairro(bairroDATA));
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Bairro>> listarTodas() {
        return ResponseEntity.ok(bairroService.getAllBairros());
    }

    @PutMapping("delete/{id}")
    public void deletarBairro(@PathVariable UUID id) {
        bairroService.deleteBairro(id);
    }

}
