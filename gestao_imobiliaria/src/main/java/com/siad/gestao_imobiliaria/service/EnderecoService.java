package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.dto.EnderecoDTO;
import com.siad.gestao_imobiliaria.exceptions.BairroException;
import com.siad.gestao_imobiliaria.exceptions.EnderecoException;
import com.siad.gestao_imobiliaria.exceptions.LogradouroException;
import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.model.Logradouro;
import com.siad.gestao_imobiliaria.repository.BairroRepository;
import com.siad.gestao_imobiliaria.repository.EnderecoRepository;
import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
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


    public Endereco createEndereco(EnderecoDTO dto) {
        Endereco endereco = new Endereco();

        if (dto.codigo() == null) {
            endereco.setCodigo(gerarProximoCodigo());
        } else {
            endereco.setCodigo(dto.codigo());
        }

        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setCep(dto.cep());

        Logradouro logradouro = logradouroRepository.findById(dto.logradouro().getId())
                .orElseThrow(() -> LogradouroException.logradouroNaoEncontrado(dto.logradouro().getId()));
        Bairro bairro = bairroRepository.findById(dto.bairro().getId())
                .orElseThrow(() -> BairroException.bairroNaoEncontrado(dto.bairro().getId()));


        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);

        return enderecoRepository.save(endereco);
    }



    public Endereco atualizar(UUID id, EnderecoDTO dto) {
        Endereco atual = enderecoRepository.findById(id)
                .orElseThrow(() -> EnderecoException.enderecoNaoEncontrado(id));

        atual.setNumero(dto.numero());
        atual.setComplemento(dto.complemento());
        atual.setCep(dto.cep());

        Logradouro logradouro = logradouroRepository.findById(dto.logradouro().getId())
                .orElseThrow(() -> LogradouroException.logradouroNaoEncontrado(dto.logradouro().getId()));
        Bairro bairro = bairroRepository.findById(dto.bairro().getId())
                .orElseThrow(() -> BairroException.bairroNaoEncontrado(dto.bairro().getId()));

        atual.setLogradouro(logradouro);
        atual.setBairro(bairro);

        return enderecoRepository.save(atual);
    }


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
