package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.ResponsavelLegalDTO;
import com.siad.gestao_imobiliaria.enums.TipoResponsavel;
import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.model.ResponsavelLegal;
import com.siad.gestao_imobiliaria.repository.ResponsavelLegalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResponsavelLegalService {

    private final ResponsavelLegalRepository responsavelLegalRepository;
    private final EnderecoService enderecoService;


    public ResponsavelLegal createResponsavelLegal(ResponsavelLegalDTO dto) {
        ResponsavelLegal responsavelLegal = new ResponsavelLegal();

        if (responsavelLegal.getCodigo() == null) {
            responsavelLegal.setCodigo(gerarProximoCodigo());
        }

        responsavelLegal.setTipoResponsavel(TipoResponsavel.valueOf(dto.tipoResponsavel().toUpperCase()));
        responsavelLegal.setNome(dto.nome());
        responsavelLegal.setTelefoneFixo(dto.telefoneFixo());
        responsavelLegal.setTelefoneCelular(dto.telefoneCelular());
        responsavelLegal.setEmail(dto.email());
        responsavelLegal.setNumeroDocumento(dto.numeroDocumento());
        responsavelLegal.setEnderecoResponsavel(enderecoService.createEndereco(dto.enderecoResponsavel()));
        return responsavelLegalRepository.save(responsavelLegal);
    }

    public List<ResponsavelLegal> getAllResponsaveis() {
        return responsavelLegalRepository.findByAtivoTrue();
    }

    public ResponsavelLegal getById(UUID id) {
        return responsavelLegalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Responsável legal não encontrado com ID: " + id));
    }

    public ResponsavelLegal updateResponsavel(UUID id, ResponsavelLegalDTO dto) {
        ResponsavelLegal responsavel = getById(id);
        responsavel.setTipoResponsavel(TipoResponsavel.valueOf(dto.tipoResponsavel().toUpperCase()));
        responsavel.setNome(dto.nome());
        responsavel.setTelefoneFixo(dto.telefoneFixo());
        responsavel.setTelefoneCelular(dto.telefoneCelular());
        responsavel.setEmail(dto.email());
        responsavel.setNumeroDocumento(dto.numeroDocumento());
        responsavel.setEnderecoResponsavel(enderecoService.createEndereco(dto.enderecoResponsavel()));

        return responsavelLegalRepository.save(responsavel);
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
