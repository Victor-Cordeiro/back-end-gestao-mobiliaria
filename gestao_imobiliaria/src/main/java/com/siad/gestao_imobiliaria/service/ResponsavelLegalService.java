package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.ResponsavelLegalDTO;
import com.siad.gestao_imobiliaria.enums.TipoResponsavel;
import com.siad.gestao_imobiliaria.model.*;
import com.siad.gestao_imobiliaria.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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
    private TipoLogradouroRepository tipoLogradouroRepository;
    private CidadeRepository cidadeRepository;
    private BairroRepository bairroRepository;


    @Transactional
    public ResponsavelLegal createResponsavelLegal(ResponsavelLegalDTO responsavelLegalDTO) {
        Endereco enderecoResponsavel = verificarOuCriarEndereco(responsavelLegalDTO.enderecoResponsavel());

        ResponsavelLegal responsavelLegal = new ResponsavelLegal();
        responsavelLegal.setCodigo(responsavelLegalDTO.codigo() != null ? responsavelLegalDTO.codigo() : gerarProximoCodigo());
        responsavelLegal.setNome(responsavelLegalDTO.nome());
        responsavelLegal.setTelefoneFixo(responsavelLegalDTO.telefoneFixo());
        responsavelLegal.setTelefoneCelular(responsavelLegalDTO.telefoneCelular());
        responsavelLegal.setEmail(responsavelLegalDTO.email());
        responsavelLegal.setNumeroDocumento(responsavelLegalDTO.numeroDocumento());
        responsavelLegal.setDataNascimento(responsavelLegalDTO.dataNascimento());
        responsavelLegal.setTipoResponsavel(TipoResponsavel.valueOf(responsavelLegalDTO.tipoResponsavel()));
        responsavelLegal.setEnderecoResponsavel(enderecoResponsavel);

        return responsavelLegalRepository.save(responsavelLegal);
    }

    private Endereco verificarOuCriarEndereco(Endereco enderecoDTO) {
        Logradouro logradouro = verificarOuCriarLogradouro(enderecoDTO.getLogradouro());

        Bairro bairro = verificarOuCriarBairro(enderecoDTO.getBairro());

        Optional<Endereco> existente = enderecoRepository.findByLogradouroAndBairroAndNumeroAndCep(
                logradouro,
                bairro,
                enderecoDTO.getNumero(),
                enderecoDTO.getCep()
        );

        return existente.orElseGet(() -> {
            Endereco novoEndereco = new Endereco();
            novoEndereco.setCodigo(enderecoDTO.getCodigo() != null ? enderecoDTO.getCodigo() : gerarProximoCodigo());
            novoEndereco.setLogradouro(logradouro);
            novoEndereco.setBairro(bairro);
            novoEndereco.setNumero(enderecoDTO.getNumero());
            novoEndereco.setComplemento(enderecoDTO.getComplemento());
            novoEndereco.setCep(enderecoDTO.getCep());
            return enderecoRepository.save(novoEndereco);
        });
    }

    private Logradouro verificarOuCriarLogradouro(Logradouro logradouroDATA) {
        if (logradouroDATA.getNome() == null) {
            throw new IllegalArgumentException("Nome do logradouro é obrigatório");
        }

        TipoLogradouro tipo = tipoLogradouroRepository.findByCodigo(logradouroDATA.getTipo().getCodigo())
                .orElseThrow(() -> new RuntimeException("Tipo de logradouro não encontrado"));

        Logradouro logradouroExistente = logradouroRepository.findFirstByNomeAndTipo(logradouroDATA.getNome(), tipo).orElse(null);
        if (logradouroExistente != null) {
            return logradouroExistente;
        }

        Logradouro novoLogradouro = new Logradouro();
        novoLogradouro.setCodigo(logradouroDATA.getCodigo() != null ? logradouroDATA.getCodigo() : gerarProximoCodigo());
        novoLogradouro.setNome(logradouroDATA.getNome());
        novoLogradouro.setNome_anterior(logradouroDATA.getNome());
        novoLogradouro.setTipo(tipo);
        novoLogradouro.setCodigo(gerarProximoCodigo());

        return logradouroRepository.save(novoLogradouro);
    }

    private Bairro verificarOuCriarBairro(Bairro bairroDATA) {
        if (bairroDATA.getNome() == null) {
            throw new IllegalArgumentException("Nome do bairro é obrigatório");
        }

        Cidade cidade = cidadeRepository.findByCodigo(bairroDATA.getCidade().getCodigo())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        Bairro bairroExistente = bairroRepository.findFirstByNomeAndCidade(bairroDATA.getNome(), cidade).orElse(null);
        if (bairroExistente != null) {
            return bairroExistente;
        }

        Bairro novoBairro = new Bairro();
        novoBairro.setNome(bairroDATA.getNome());
        novoBairro.setCidade(cidade);
        novoBairro.setCodigo(gerarProximoCodigo());

        return bairroRepository.save(novoBairro);
    }

    @Transactional
    public ResponsavelLegal atualizar(UUID id, ResponsavelLegal dadosAtualizados) {
        ResponsavelLegal responsavelAtual = responsavelLegalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Responsável legal não encontrado com ID: " + id));

        responsavelAtual.setTipoResponsavel(dadosAtualizados.getTipoResponsavel());
        responsavelAtual.setNome(dadosAtualizados.getNome());
        responsavelAtual.setTelefoneFixo(dadosAtualizados.getTelefoneFixo());
        responsavelAtual.setTelefoneCelular(dadosAtualizados.getTelefoneCelular());
        responsavelAtual.setEmail(dadosAtualizados.getEmail());
        responsavelAtual.setNumeroDocumento(dadosAtualizados.getNumeroDocumento());

        Endereco endereco = verificarOuCriarEndereco(dadosAtualizados.getEnderecoResponsavel());
        responsavelAtual.setEnderecoResponsavel(endereco);

        return responsavelLegalRepository.save(responsavelAtual);
    }


    public List<ResponsavelLegal> getAllResponsaveis() {
        return responsavelLegalRepository.findByAtivoTrue();
    }

    public ResponsavelLegal getById(UUID id) {
        return responsavelLegalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Responsável legal não encontrado com ID: " + id));
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
