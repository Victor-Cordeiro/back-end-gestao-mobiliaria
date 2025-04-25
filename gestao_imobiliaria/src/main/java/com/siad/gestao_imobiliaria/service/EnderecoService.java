package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.repository.BairroRepository;
import com.siad.gestao_imobiliaria.repository.EnderecoRepository;
import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;


    private final LogradouroRepository logradouroRepository;
    private final BairroRepository bairroRepository;

    public Endereco createEndereco(String numero, String complemento, String cep, UUID logradouroId, UUID bairroId) {
        Endereco endereco = new Endereco();
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setCep(cep);
        endereco.setLogradouro(logradouroRepository.findById(logradouroId).orElse(null));
        endereco.setBairro(bairroRepository.findById(bairroId).orElse(null));
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
