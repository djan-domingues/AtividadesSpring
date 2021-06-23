package com.atividade01.atividade01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atividade01")
public class Atividade01Controller {
	
	@GetMapping
	public String atividade01() {
		return "Para realizar essa atividade eu tive que exercitar muito a Persistência e a atenção aos detalhes";
	}

}
