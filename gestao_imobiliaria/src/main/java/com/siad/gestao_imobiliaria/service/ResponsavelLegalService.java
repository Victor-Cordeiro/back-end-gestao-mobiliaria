package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.EnderecoDTO;
import com.siad.gestao_imobiliaria.dto.ResponsavelLegalDTO;
import com.siad.gestao_imobiliaria.enums.TipoResponsavel;
import com.siad.gestao_imobiliaria.exceptions.ResponsavelLegalException;
import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.model.ResponsavelLegal;
import com.siad.gestao_imobiliaria.model.TipoLogradouro;
import com.siad.gestao_imobiliaria.repository.EnderecoRepository;
import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
import com.siad.gestao_imobiliaria.repository.ResponsavelLegalRepository;
import com.siad.gestao_imobiliaria.repository.TipoLogradouroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResponsavelLegalService {

    private LogradouroRepository logradouroRepository;
    private EnderecoRepository enderecoRepository;
    private ResponsavelLegalRepository responsavelLegalRepository;

    public ResponsavelLegal createResponsavelLegal(ResponsavelLegalDTO dto) {
        Endereco enderecoResponsavel = verificarOuCriarEndereco(dto.enderecoResponsavel());
        ResponsavelLegal responsavelLegal = new ResponsavelLegal();
        responsavelLegal.setNome(dto.nome());
        responsavelLegal.setTelefoneFixo(dto.telefoneFixo());
        responsavelLegal.setTelefoneCelular(dto.telefoneCelular());
        responsavelLegal.setEmail(dto.email());
        responsavelLegal.setNumeroDocumento(dto.numeroDocumento());
        responsavelLegal.setTipoResponsavel(TipoResponsavel.valueOf(dto.tipoResponsavel()));
        responsavelLegal.setEnderecoResponsavel(enderecoResponsavel);

        return responsavelLegalRepository.save(responsavelLegal);
    }



    private Endereco verificarOuCriarEndereco(Endereco enderecoDTO) {
        Logradouro logradouro = logradouroRepository.findByNome(enderecoDTO.getLogradouro().getNome());

        if (logradouro == null) {
            logradouro = logradouroRepository.save(enderecoDTO.getLogradouro());
        }
        Logradouro fLogradouro = logradouro;

        Optional<Endereco> existente = enderecoRepository.findByLogradouroAndBairroAndNumeroAndCep(
                logradouro,
                enderecoDTO.getBairro(),
                enderecoDTO.getNumero(),
                enderecoDTO.getCep()
        );

        return existente.orElseGet(() -> {
            Endereco novoEndereco = new Endereco();
            novoEndereco.setLogradouro(fLogradouro);
            novoEndereco.setBairro(enderecoDTO.getBairro());
            novoEndereco.setNumero(enderecoDTO.getNumero());
            novoEndereco.setComplemento(enderecoDTO.getComplemento());
            novoEndereco.setCep(enderecoDTO.getCep());
            return enderecoRepository.save(novoEndereco);
        });
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

//        Endereco endereco = enderecoRepository.findById(dto.enderecoResponsavel().getId())
//                .orElseThrow(() -> new RuntimeException("ewndereco não encontrado"));
//        responsavelAtual.setEnderecoResponsavel(endereco);

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
