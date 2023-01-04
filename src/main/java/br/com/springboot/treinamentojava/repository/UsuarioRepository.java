package br.com.springboot.treinamentojava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.treinamentojava.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


	Usuario findByLogin(String login);
	Usuario findByCpf(String cpf);
	Usuario findByEmail(String email);
}
