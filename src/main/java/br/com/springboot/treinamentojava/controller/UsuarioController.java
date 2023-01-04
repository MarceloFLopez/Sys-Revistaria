package br.com.springboot.treinamentojava.controller;

import java.util.ArrayList;
import java.util.List;
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

import br.com.springboot.treinamentojava.model.Papel;
import br.com.springboot.treinamentojava.model.Usuario;
import br.com.springboot.treinamentojava.repository.PapelRepository;
import br.com.springboot.treinamentojava.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PapelRepository papelRepository;
	
	@GetMapping("/novo")
	public String adicionarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/publica-criar-usuario";
	}
	
	@PostMapping("/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result, 
				Model nodel, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/publica-criar-usuario";
		}	
		
		Usuario usrLogin = usuarioRepository.findByLogin(usuario.getLogin());
		Usuario usrCpf = usuarioRepository.findByCpf(usuario.getCpf());
		Usuario usrEmail = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (usrLogin != null) {
			nodel.addAttribute("loginExiste", "Login já cadastrado");
			return "/publica-criar-usuario";
		}else if(usrCpf != null){
			nodel.addAttribute("loginExiste", "Login já cadastrado");
			return "/publica-criar-usuario";
		}else if(usrEmail != null){
			nodel.addAttribute("loginExiste", "Login já cadastrado");
			return "/publica-criar-usuario";
		}
		
		// Busca o papel básico de usuário
		Papel papel = papelRepository.findByPapel("USER");
		List<Papel> pepeis = new ArrayList<Papel>();
		pepeis.add(papel);
		usuario.setPapeis(pepeis);//Associa o papel ao usuário
		
		usuarioRepository.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return "redirect:/usuario/novo";
	}
	
	@RequestMapping("/admin/listar")
	public String listarUsuario(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());		
		return "/auth/admin/admin-listar-usuario";		
	}
	
	@GetMapping("/admin/apagar/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		usuarioRepository.delete(usuario);
	    return "redirect:/usuario/admin/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") long id, Model model) {
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if (!usuarioVelho.isPresent()) {
            throw new IllegalArgumentException("Usuário inválido:" + id);
        } 
		Usuario usuario = usuarioVelho.get();
	    model.addAttribute("usuario", usuario);
	    return "/auth/user/user-alterar-usuario";
	}
	
	@PostMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") long id, 
			@Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
	    	usuario.setId(id);
	        return "/auth/user/user-alterar-usuario";
	    }
	    usuarioRepository.save(usuario);
	    return "redirect:/usuario/admin/listar";
	}

	@GetMapping("/editarPapel/{id}")
	public String selecionarPapel(@PathVariable("id") long id, Model model) {
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if (!usuarioVelho.isPresent()) {
            throw new IllegalArgumentException("Usuário inválido:" + id);
        } 
		Usuario usuario = usuarioVelho.get();
	    model.addAttribute("usuario", usuario);
	    model.addAttribute("listaPapeis", papelRepository.findAll());
	    return "/auth/admin/admin-editar-papel-usuario";
	}
	
	

}
