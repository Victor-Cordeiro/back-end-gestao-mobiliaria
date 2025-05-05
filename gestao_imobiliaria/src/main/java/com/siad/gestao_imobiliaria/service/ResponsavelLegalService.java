package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.ResponsavelLegalDTO;
import com.siad.gestao_imobiliaria.enums.TipoResponsavel;
import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.model.ResponsavelLegal;
import com.siad.gestao_imobiliaria.repository.EnderecoRepository;
import com.siad.gestao_imobiliaria.repository.ResponsavelLegalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResponsavelLegalService {

    private final ResponsavelLegalRepository responsavelLegalRepository;
    private final EnderecoService enderecoService;
   private final EnderecoRepository enderecoRepository;


    public ResponsavelLegal createResponsavelLegal(ResponsavelLegalDTO dto) {
        ResponsavelLegal responsavelLegal = new ResponsavelLegal();

        Endereco endereco = enderecoRepository.findById(dto.enderecoResponsavel().getId())
                .orElseThrow(() -> new RuntimeException("ewndereco não encontrado"));


        if (responsavelLegal.getCodigo() == null) {
            responsavelLegal.setCodigo(gerarProximoCodigo());
        }

        responsavelLegal.setTipoResponsavel(TipoResponsavel.valueOf(dto.tipoResponsavel().toUpperCase()));
        responsavelLegal.setNome(dto.nome());
        responsavelLegal.setTelefoneFixo(dto.telefoneFixo());
        responsavelLegal.setTelefoneCelular(dto.telefoneCelular());
        responsavelLegal.setEmail(dto.email());
        responsavelLegal.setNumeroDocumento(dto.numeroDocumento());
        responsavelLegal.setEnderecoResponsavel(endereco);
        return responsavelLegalRepository.save(responsavelLegal);
    }

    public List<ResponsavelLegal> getAllResponsaveis() {
        return responsavelLegalRepository.findByAtivoTrue();
    }

    public ResponsavelLegal getById(UUID id) {
        return responsavelLegalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Responsável legal não encontrado com ID: " + id));
    }



    public ResponsavelLegal atualizar(UUID id, ResponsavelLegalDTO dto) {
        ResponsavelLegal responsavelAtual = responsavelLegalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Responsável legal não encontrado com ID: " + id));

        responsavelAtual.setTipoResponsavel(TipoResponsavel.valueOf(dto.tipoResponsavel().toUpperCase()));
        responsavelAtual.setNome(dto.nome());
        responsavelAtual.setTelefoneFixo(dto.telefoneFixo());
        responsavelAtual.setTelefoneCelular(dto.telefoneCelular());
        responsavelAtual.setEmail(dto.email());
        responsavelAtual.setNumeroDocumento(dto.numeroDocumento());

        Endereco endereco = enderecoRepository.findById(dto.enderecoResponsavel().getId())
                .orElseThrow(() -> new RuntimeException("ewndereco não encontrado"));
        responsavelAtual.setEnderecoResponsavel(endereco);

        return responsavelLegalRepository.save(responsavelAtual);
    }



    public void deletar(UUID id) {
        ResponsavelLegal responsavelLegal = getById(id);
        responsavelLegal.setAtivo(false);
        responsavelLegalRepository.save(responsavelLegal);
    }

    public Long gerarProximoCodigo() {
        Long maior = responsavelLegalRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }
}
