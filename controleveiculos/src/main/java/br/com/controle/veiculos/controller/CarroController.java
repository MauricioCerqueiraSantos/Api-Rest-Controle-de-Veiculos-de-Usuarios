package br.com.controle.veiculos.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.veiculos.events.RecursoCriadoEvent;
import br.com.controle.veiculos.model.Carro;
import br.com.controle.veiculos.service.CarroService;

@RestController
@RequestMapping(path = "/CadastroVeiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarroController {
	
	private CarroService carroService;
	private ApplicationEventPublisher publisher;
	
	@Autowired
	public CarroController(CarroService carroService, ApplicationEventPublisher publisher) {
		this.carroService = carroService;
		this.publisher = publisher;
	}
	
	
	@GetMapping
	public List<Carro> listCarro(){
		return carroService.listCarro();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Carro> getIdCarro(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(carroService.getCarro(id));
	}	
	
	@PostMapping
	public ResponseEntity<Carro> saveUsuario(@Validated @RequestBody Carro carro, HttpServletResponse response){
		Carro c = carroService.saveCarro(carro);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, c.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Carro> updateCarro(@PathVariable Long id, @RequestBody Carro carro){
		return ResponseEntity.ok(carroService.updateCarro(id, carro));
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCarro(@PathVariable Long id) {
		carroService.deleteCarro(id);
	}
		
}