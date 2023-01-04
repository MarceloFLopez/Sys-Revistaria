package br.com.springboot.treinamentojava.componente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.springboot.treinamentojava.model.Papel;
import br.com.springboot.treinamentojava.repository.PapelRepository;

@Component
public class CarregadoraDados implements CommandLineRunner {

	@Autowired
	private PapelRepository papelReposityory;

	@Override
	public void run(String... args) throws Exception {
		String[] papeis = { "ADMIN", "USER", "BIBLIOTECARIO" };

		for (String papelString : papeis) {
			Papel papel = papelReposityory.findByPapel(papelString);
			if (papel == null) {
				papel = new Papel(papelString);
				papelReposityory.save(papel);
			}
		}

	}

}
