package br.com.springboot.treinamentojava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/") 
	public String index(Model model) {
		model.addAttribute("msnBemVindo", "Bem-vindo à Sys-Revistaria");
		return "publica-index";
	}
	
	@RequestMapping("/exemplo") 
	public String exemplo(Model model) {
		model.addAttribute("msnBemVindo", "Bem-vindo à Sys-Revistaria");
		return "exemplo";
	}

}
