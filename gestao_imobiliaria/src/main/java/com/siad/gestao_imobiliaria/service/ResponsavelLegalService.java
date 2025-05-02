package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.dto.ResponsavelLegalDTO;
import com.siad.gestao_imobiliaria.enums.TipoResponsavel;
import com.siad.gestao_imobiliaria.model.ResponsavelLegal;
import com.siad.gestao_imobiliaria.repository.ResponsavelLegalRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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





    public Long gerarProximoCodigo() {
        Long maior = responsavelLegalRepository.findMaxCodigo();
        return (maior == null) ? 1L : maior + 1;
    }
}
