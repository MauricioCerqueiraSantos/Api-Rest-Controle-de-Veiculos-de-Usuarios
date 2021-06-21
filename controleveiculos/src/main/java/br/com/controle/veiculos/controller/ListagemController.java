package br.com.controle.veiculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.veiculos.model.Carro;
import br.com.controle.veiculos.repository.CarroRepository;

@RestController
@RequestMapping(path = "/ListagemVeiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ListagemController {
	
	@Autowired
	CarroRepository carroRepository;
	
	@GetMapping	
	public List<Carro> listCarros(){
		return carroRepository.findAll();
	}

	
}
