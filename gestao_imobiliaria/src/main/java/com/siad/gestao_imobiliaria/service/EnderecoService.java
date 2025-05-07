package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.dto.BairroDTO;
import com.siad.gestao_imobiliaria.dto.EnderecoDTO;
import com.siad.gestao_imobiliaria.dto.LogradouroDTO;
import com.siad.gestao_imobiliaria.exceptions.BairroException;
import com.siad.gestao_imobiliaria.exceptions.EnderecoException;
import com.siad.gestao_imobiliaria.exceptions.LogradouroException;
import com.siad.gestao_imobiliaria.model.*;
import com.siad.gestao_imobiliaria.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Endereco createEndereco(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setCodigo(dto.codigo() != null ? dto.codigo() : gerarProximoCodigo());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setCep(dto.cep());

        Logradouro logradouro = verificarOuCriarLogradouro(dto.logradouro());
        Bairro bairro = verificarOuCriarBairro(dto.bairro());

        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);

        return enderecoRepository.save(endereco);
    }





    private Logradouro verificarOuCriarLogradouro(Logradouro dto) {
        if (dto.getNome() == null) {
            throw new IllegalArgumentException("Nome do logradouro é obrigatório");
        }

        Logradouro existente = logradouroRepository.findByNome(dto.getNome());
        if (existente != null) {
            return existente;
        }

        TipoLogradouro tipo = tipoLogradouroRepository.findByCodigo(dto.getTipo().getCodigo())
                .orElseThrow(() -> new RuntimeException("Tipo de logradouro não encontrado"));

        Logradouro novo = new Logradouro();
        novo.setNome(dto.getNome());
        novo.setTipo(tipo);
        novo.setCodigo(gerarProximoCodigo());

        return logradouroRepository.save(novo);
    }







    private Bairro verificarOuCriarBairro(Bairro dto) {
        if (dto.getNome() == null) {
            throw new IllegalArgumentException("Nome do bairro é obrigatório");
        }

        Cidade cidade = cidadeRepository.findByCodigo(dto.getCidade().getCodigo())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        Bairro existente = bairroRepository.findFirstByNomeAndCidade(dto.getNome(), cidade).orElse(null);
        if (existente != null) {
            return existente;
        }

        Bairro novo = new Bairro();
        novo.setNome(dto.getNome());
        novo.setCidade(cidade);
        novo.setCodigo(gerarProximoCodigo());

        return bairroRepository.save(novo);
    }











//    public Endereco atualizar(UUID id, EnderecoDTO dto) {
//        Endereco atual = enderecoRepository.findById(id)
//                .orElseThrow(() -> EnderecoException.enderecoNaoEncontrado(id));
//
//        atual.setNumero(dto.numero());
//        atual.setComplemento(dto.complemento());
//        atual.setCep(dto.cep());
//
//        Logradouro logradouro = logradouroRepository.findById(dto.logradouro().getId())
//                .orElseThrow(() -> LogradouroException.logradouroNaoEncontrado(dto.logradouro().getId()));
//        Bairro bairro = bairroRepository.findById(dto.bairro().getId())
//                .orElseThrow(() -> BairroException.bairroNaoEncontrado(dto.bairro().getId()));
//
//        atual.setLogradouro(logradouro);
//        atual.setBairro(bairro);
//
//        return enderecoRepository.save(atual);
//    }


    public Endereco buscarPorId(UUID id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> EnderecoException.enderecoNaoEncontrado(id));

    }
    public Endereco buscarPorCep(String cep) {
        return enderecoRepository.findByCep(cep)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
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
