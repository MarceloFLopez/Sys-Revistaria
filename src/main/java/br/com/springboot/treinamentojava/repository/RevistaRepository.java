package br.com.springboot.treinamentojava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.treinamentojava.model.Revista;

@Repository
public interface RevistaRepository extends JpaRepository<Revista, Long>{

}
