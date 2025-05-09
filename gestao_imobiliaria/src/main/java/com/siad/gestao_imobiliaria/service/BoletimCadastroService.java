package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.dto.BoletimCadastroDTO;
import com.siad.gestao_imobiliaria.exceptions.BoletimException;
import com.siad.gestao_imobiliaria.exceptions.CidadeException;
import com.siad.gestao_imobiliaria.exceptions.ResponsavelLegalException;
import com.siad.gestao_imobiliaria.exceptions.TipoLogradouroException;
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
public class BoletimCadastroService {

    private final BoletimCadastroRepository boletimRepository;
    private final EnderecoRepository enderecoRepository;
    private final ResponsavelLegalRepository reposponsavelLegalRepository;
    private final LogradouroRepository logradouroRepository;
    private final TipoLogradouroRepository tipoLogradouroRepository;
    private final BairroRepository bairroRepository;
    private final CidadeRepository cidadeRepository;


    @Transactional
    public BoletimCadastro createBoletimCadastro(BoletimCadastroDTO boletimCadastroDTO) {
        BoletimCadastro boletim = new BoletimCadastro();

        ResponsavelLegal responsavel = reposponsavelLegalRepository.findByCodigo(boletimCadastroDTO.responsavel().getCodigo())
                .orElseThrow(() -> ResponsavelLegalException.responsavelCodigoNaoEncontrado(boletimCadastroDTO.responsavel().getCodigo()));

        Endereco enderecoCorrespondencia = verificarOuCriarEndereco(boletimCadastroDTO.enderecoCorrespondencia());
        Endereco enderecoImovel = verificarOuCriarEndereco(boletimCadastroDTO.enderecoImovel());

        boletim.setCodigo(gerarProximoCodigo());
        boletim.setMatricula(boletimCadastroDTO.matricula());
        boletim.setResponsavel(responsavel);
        boletim.setEnderecoCorrespondencia(enderecoCorrespondencia);
        boletim.setEnderecoImovel(enderecoImovel);

        return boletimRepository.save(boletim);
    }


    private Endereco verificarOuCriarEndereco(Endereco enderecoData) {
        Logradouro logradouro = verificarOuCriarLogradouro(enderecoData.getLogradouro());
        Bairro bairro = verificarOuCriarBairro(enderecoData.getBairro());

        Optional<Endereco> existente = enderecoRepository.findByLogradouroAndBairroAndNumeroAndCep(
                logradouro,
                bairro,
                enderecoData.getNumero(),
                enderecoData.getCep()
        );

        if (existente.isPresent()) {
            return existente.get(); // Já está persistido
        }

        Endereco novo = new Endereco();
        novo.setCodigo(gerarProximoCodigo());
        novo.setNumero(enderecoData.getNumero());
        novo.setComplemento(enderecoData.getComplemento());
        novo.setCep(enderecoData.getCep());
        novo.setLogradouro(logradouro);
        novo.setBairro(bairro);

        return enderecoRepository.save(novo); // GARANTIA DE PERSISTÊNCIA
    }




    private Logradouro verificarOuCriarLogradouro(Logradouro logradouroDATA) {
        TipoLogradouro tipo = tipoLogradouroRepository.findByCodigo(logradouroDATA.getTipo().getCodigo())
                .orElseThrow(() -> TipoLogradouroException.tipoLogradouroNaoEncontrado(logradouroDATA.getTipo().getCodigo()));


        return logradouroRepository.findFirstByNomeAndTipo(logradouroDATA.getNome(), tipo)
                .orElseGet(() -> {
                    Logradouro novo = new Logradouro();
                    novo.setCodigo(gerarProximoCodigo());
                    novo.setNome(logradouroDATA.getNome());
                    novo.setNome_anterior(logradouroDATA.getNome());
                    novo.setTipo(tipo);
                    return logradouroRepository.save(novo);
                });
    }

    private Bairro verificarOuCriarBairro(Bairro bairroDATA) {
        Cidade cidade = cidadeRepository.findByCodigo(bairroDATA.getCidade().getCodigo())
                .orElseThrow(() -> CidadeException.cidadeNaoEncontrada(bairroDATA.getCidade().getCodigo()));

        return bairroRepository.findFirstByNomeAndCidade(bairroDATA.getNome(), cidade)
                .orElseGet(() -> {
                    Bairro novo = new Bairro();
                    novo.setCodigo(gerarProximoCodigo());
                    novo.setNome(bairroDATA.getNome());
                    novo.setCidade(cidade);
                    return bairroRepository.save(novo);
                });
    }




    public List<BoletimCadastro> getAll() {
        return boletimRepository.findByAtivoTrue();
    }

    public BoletimCadastro getById(UUID id) {
        return boletimRepository.findById(id)
                .orElseThrow(() -> BoletimException.boletimNaoEncontrado(id));
    }

    @Transactional
    public BoletimCadastro update(UUID id, BoletimCadastro boletimCadastroDATA) {
        BoletimCadastro boletim = boletimRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Boletim não encontrado com ID: " + id));

        boletim.setMatricula(boletimCadastroDATA.getMatricula());

        ResponsavelLegal responsavelLegal = reposponsavelLegalRepository
                .findByCodigo(boletimCadastroDATA.getResponsavel().getCodigo())
                .orElseThrow(() -> ResponsavelLegalException.responsavelCodigoNaoEncontrado(boletimCadastroDATA.getResponsavel().getCodigo()));

        Endereco enderecoCorrespondencia = verificarOuCriarEndereco(boletimCadastroDATA.getEnderecoCorrespondencia());
        Endereco enderecoImovel = verificarOuCriarEndereco(boletimCadastroDATA.getEnderecoImovel());

        boletim.setResponsavel(responsavelLegal);
        boletim.setEnderecoCorrespondencia(enderecoCorrespondencia);
        boletim.setEnderecoImovel(enderecoImovel);

        return boletimRepository.save(boletim);
    }


    public void delete(UUID id) {
        BoletimCadastro boletim = getById(id);
        boletim.setAtivo(false);
        boletimRepository.save(boletim);
    }

    public Long gerarProximoCodigo() {
        Long maior = boletimRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }

}
