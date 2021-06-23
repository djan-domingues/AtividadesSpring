package com.atividade02.atividade02.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atividade02")

public class Atividade02Controller {
	
	@GetMapping
	public String atividade02() {
		return"Meus objetivos de aprendizagem para essa semana é compreender as novas matérias, treinar os novos conhecimentos e rever Java";
		
	}

}
