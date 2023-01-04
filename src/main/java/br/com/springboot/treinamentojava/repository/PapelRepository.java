package br.com.springboot.treinamentojava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.treinamentojava.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long>{

	Papel findByPapel(String papel);
}
