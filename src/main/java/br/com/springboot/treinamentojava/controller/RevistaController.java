package br.com.springboot.treinamentojava.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.treinamentojava.model.Revista;
import br.com.springboot.treinamentojava.repository.RevistaRepository;

@Controller
@RequestMapping("/revista")
public class RevistaController {

	@Autowired
	private RevistaRepository revistaRepository;

	@GetMapping("/novo")
	public String adicionarRevista(Model model) {
		model.addAttribute("revista", new Revista());
		return "/publica-criar-revista";
	}

	@PostMapping("/salvar")
	public String salvarRevista(@Valid Revista revista, BindingResult result, Model model,
			RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			
			return "/publica-criar-revista";
		}
		revistaRepository.save(revista);
		attributes.addFlashAttribute("mensagem", "Revista salvo com sucesso!");
		return "redirect:/revista/novo";
	}

	@RequestMapping("/user/listar")
	public String listarRevista(Model model) {
		model.addAttribute("revistas", revistaRepository.findAll());
		return "/auth/user/listar-revista";
	}

	@GetMapping("/user/apagar/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Revista revista = revistaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		revistaRepository.delete(revista);
		return "redirect:/revista/user/listar";
	}

	@GetMapping("/editar/{id}")
	public String editarRevista(@PathVariable("id") long id, Model model) {
		Optional<Revista> revistaVelho = revistaRepository.findById(id);
		if (!revistaVelho.isPresent()) {
			throw new IllegalArgumentException("Usuário inválido:" + id);
		}
		Revista revista = revistaVelho.get();
		model.addAttribute("revista", revista);
		return "/auth/user/user-alterar-revista";
	}

	@PostMapping("/editar/{id}")
	public String editarRevista(@PathVariable("id") long id, @Valid Revista revista, BindingResult result) {
		if (result.hasErrors()) {
			revista.setId(id);
			return "/auth/user/user-alterar-usuario";
		}
		revistaRepository.save(revista);
		return "redirect:/revista/user/listar";
	}

}
