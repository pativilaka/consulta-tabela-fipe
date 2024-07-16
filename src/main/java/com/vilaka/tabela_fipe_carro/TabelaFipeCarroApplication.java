package com.vilaka.tabela_fipe_carro;

import com.vilaka.tabela_fipe_carro.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipeCarroApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeCarroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("*** Listagem de Tabela FIPE ***");

		Principal principal = new Principal();
		principal.exibeMenu();

	}
}
