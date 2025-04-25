package com.siad.gestao_imobiliaria.service;


import com.siad.gestao_imobiliaria.repository.LogradouroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogradouroService {

    private final LogradouroRepository logradouroRepository;
}
