package br.com.springboot.treinamentojava.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Revista implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 5, message = "O titulo deve ter no mínimo 5 caracteres")
	private String titulo;

	@Size(min = 5, message = "O titulo deve ter no mínimo 5 caracteres")
	@NotNull
	private String Autor;
	
	@NotNull( message = "O titulo deve ter no mínimo 5 caracteres")
	@Column(unique = true, length = 5)
	private Long ean;
}
