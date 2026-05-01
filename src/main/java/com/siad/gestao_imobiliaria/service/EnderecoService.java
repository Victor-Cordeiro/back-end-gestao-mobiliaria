package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.dto.EnderecoDTO;
import com.siad.gestao_imobiliaria.exceptions.CidadeException;
import com.siad.gestao_imobiliaria.exceptions.EnderecoException;
import com.siad.gestao_imobiliaria.exceptions.TipoLogradouroException;
import com.siad.gestao_imobiliaria.model.*;
import com.siad.gestao_imobiliaria.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final LogradouroRepository logradouroRepository;
    private final BairroRepository bairroRepository;
    private final TipoLogradouroRepository tipoLogradouroRepository;
    private final CidadeRepository cidadeRepository;

    @Transactional
    public Endereco createEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setCodigo(enderecoDTO.codigo() != null ? enderecoDTO.codigo() : gerarProximoCodigo());
        endereco.setNumero(enderecoDTO.numero());
        endereco.setComplemento(enderecoDTO.complemento());
        endereco.setCep(enderecoDTO.cep());

        Logradouro logradouro = verificarOuCriarLogradouro(enderecoDTO.logradouro());
        Bairro bairro = verificarOuCriarBairro(enderecoDTO.bairro());

        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);

        return enderecoRepository.save(endereco);
    }

    private Logradouro verificarOuCriarLogradouro(Logradouro logradouroDATA) {
        if (logradouroDATA.getNome() == null) {
            throw new IllegalArgumentException("Nome do logradouro é obrigatório");
        }

        TipoLogradouro tipo = tipoLogradouroRepository.findByCodigo(logradouroDATA.getTipo().getCodigo())
                .orElseThrow(() -> TipoLogradouroException.tipoLogradouroNaoEncontrado(logradouroDATA.getTipo().getCodigo()));

        Logradouro lograExistente = logradouroRepository.findFirstByNomeAndTipo(logradouroDATA.getNome(), tipo).orElse(null);
        if (lograExistente != null) {
            return lograExistente;
        }

        Logradouro novo = new Logradouro();
        novo.setNome(logradouroDATA.getNome());
        novo.setNome_anterior(logradouroDATA.getNome());
        novo.setTipo(tipo);
        novo.setCodigo(gerarProximoCodigo());

        return logradouroRepository.save(novo);
    }

    private Bairro verificarOuCriarBairro(Bairro bairroDATA) {
        if (bairroDATA.getNome() == null) {
            throw new IllegalArgumentException("Nome do bairro é obrigatório");
        }

        Cidade cidade = cidadeRepository.findByCodigo(bairroDATA.getCidade().getCodigo())
                .orElseThrow(() -> CidadeException.cidadeNaoEncontrada(bairroDATA.getCidade().getCodigo()));

        Bairro existente = bairroRepository.findFirstByNomeAndCidade(bairroDATA.getNome(), cidade).orElse(null);
        if (existente != null) {
            return existente;
        }



        Bairro novo = new Bairro();
        novo.setNome(bairroDATA.getNome());
        novo.setCidade(cidade);
        novo.setCodigo(gerarProximoCodigo());

        return bairroRepository.save(novo);
    }

    public Endereco atualizar(UUID id, EnderecoDTO enderecoDTO) {
        Endereco atual = enderecoRepository.findById(id)
                .orElseThrow(() -> EnderecoException.enderecoNaoEncontrado(id));

        atual.setNumero(enderecoDTO.numero());
        atual.setComplemento(enderecoDTO.complemento());
        atual.setCep(enderecoDTO.cep());

        TipoLogradouro tipo = tipoLogradouroRepository.findByCodigo(enderecoDTO.logradouro().getTipo().getCodigo())
                .orElseThrow(() -> TipoLogradouroException.tipoLogradouroNaoEncontrado(enderecoDTO.logradouro().getTipo().getCodigo()));

        Optional<Logradouro> optLogra = logradouroRepository.findFirstByNomeAndTipo(enderecoDTO.logradouro().getNome(), tipo);
        Logradouro logradouro;
        if (optLogra.isPresent()) {
            logradouro = optLogra.get();
        } else {
            Logradouro logAtual = atual.getLogradouro();
            logAtual.setNome_anterior(logAtual.getNome());
            logAtual.setNome(enderecoDTO.logradouro().getNome());
            logradouro = logradouroRepository.save(logAtual);
        }

        Cidade cidade = cidadeRepository.findByCodigoAndAtivoTrue(enderecoDTO.bairro().getCidade().getCodigo())
                .orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada ou inativa."));


        Optional<Bairro> optBairro = bairroRepository.findFirstByNomeAndCidade(enderecoDTO.bairro().getNome(), cidade);
        Bairro bairro = optBairro.orElseGet(() -> {
            Bairro novo = new Bairro();
            novo.setCodigo(gerarProximoCodigo());
            novo.setNome(enderecoDTO.bairro().getNome());
            novo.setCidade(cidade);
            novo.setAtivo(true);
            return bairroRepository.save(novo);
        });

        atual.setLogradouro(logradouro);
        atual.setBairro(bairro);

        return enderecoRepository.save(atual);
    }


    public void deletar(UUID id) {
        Endereco endereco = getEnderecoById(id);
        endereco.setAtivo(false);;
        enderecoRepository.save(endereco);
    }

    private Endereco getEnderecoById(UUID id) {
        return enderecoRepository.findById(id).orElseThrow(() -> EnderecoException.enderecoNaoEncontrado(id));
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findByAtivoTrue();
    }

    public Long gerarProximoCodigo() {
        Long maior = enderecoRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }

}
