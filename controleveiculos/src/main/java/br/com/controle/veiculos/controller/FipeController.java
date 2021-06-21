package br.com.controle.veiculos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.veiculos.consumer.Fipe;
import br.com.controle.veiculos.repository.FipeRepository;

@RestController
@RequestMapping("https://parallelum.com.br/fipe/api/v1/carros/marcas")
public class FipeController {
	
	@Autowired
	FipeRepository fipeRepository;
	
	@GetMapping()
	public Fipe getFipe() {
		return fipeRepository.getByFipe("https://parallelum.com.br/fipe/api/v1/carros/marcas");
	}

}
