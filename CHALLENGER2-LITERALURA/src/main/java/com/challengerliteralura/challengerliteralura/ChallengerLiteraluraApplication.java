package com.challengerliteralura.challengerliteralura;

import com.challengerliteralura.challengerliteralura.client.ClienteLiteralura;
import com.challengerliteralura.challengerliteralura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.challengerliteralura.challengerliteralura.repository.LivroRepository;

@SpringBootApplication
public class ChallengerLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository livroRepositorio;
	@Autowired
	private AutorRepository autorRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(ChallengerLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ClienteLiteralura client = new ClienteLiteralura(livroRepositorio, autorRepositorio);
		client.menu();
	}

}
