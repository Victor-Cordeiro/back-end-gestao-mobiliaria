package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;



    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }


    public Endereco atualizar(UUID id, Endereco novo) {
        Endereco atual = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        atual.setCep(novo.getCep());
        atual.setNumero(novo.getNumero());
        atual.setComplemento(novo.getComplemento());
        atual.setLogradouro(novo.getLogradouro());
        atual.setBairro(novo.getBairro());

        return enderecoRepository.save(atual);
    }


    public Endereco buscarPorId(UUID id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }
    public Endereco buscarPorCep(String cep) {
        return enderecoRepository.findAll().stream()
                .filter(endereco -> endereco.getCep().equals(cep))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }


    public void deletar(UUID id) {
        enderecoRepository.deleteById(id);
    }


    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }



}
