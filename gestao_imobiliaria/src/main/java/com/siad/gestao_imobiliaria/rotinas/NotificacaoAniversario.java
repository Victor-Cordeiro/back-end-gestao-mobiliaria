package com.siad.gestao_imobiliaria.rotinas;

import com.siad.gestao_imobiliaria.model.ResponsavelLegal;
import com.siad.gestao_imobiliaria.repository.ResponsavelLegalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificacaoAniversario{


    @Autowired
    private ResponsavelLegalRepository responsavelRepository;

    @Scheduled(cron = "0 23 10 * * *") // Todo dia às 08:00
    public void verificarAniversariantes() {
        LocalDate hoje = LocalDate.now();

        List<ResponsavelLegal> aniversariantes = responsavelRepository
                .buscarPorDiaEMes(hoje.getDayOfMonth(), hoje.getMonthValue());

        for (ResponsavelLegal r : aniversariantes) {
            System.out.println("🎉 Feliz aniversário, " + r.getNome() + "!");

        }
    }


}
