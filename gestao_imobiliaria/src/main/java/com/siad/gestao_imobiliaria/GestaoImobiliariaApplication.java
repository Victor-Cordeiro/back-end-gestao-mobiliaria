package com.siad.gestao_imobiliaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestaoImobiliariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoImobiliariaApplication.class, args);
		System.out.println("Rodando");
	}

}
