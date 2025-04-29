package com.siad.gestao_imobiliaria.service;

import com.siad.gestao_imobiliaria.model.Bairro;
import com.siad.gestao_imobiliaria.model.Endereco;
import com.siad.gestao_imobiliaria.model.Logradouro;
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
        Logradouro logradouro = logradouroRepository.findById(logradouroId).orElseThrow(() -> new RuntimeException("Logradouro não encontrado"));
        Bairro bairro = bairroRepository.findById(bairroId).orElseThrow(() -> new RuntimeException("Bairro não encontrado"));

        Endereco endereco = new Endereco();
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setCep(cep);
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);

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
        return enderecoRepository.findByCep(cep)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }




    public void deletar(UUID id) {

        enderecoRepository.deleteById(id);
    }


    public List<Endereco> listarTodos() {

        return enderecoRepository.findAll();
    }



}
