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
import br.com.controle.veiculos.model.Usuario;
import br.com.controle.veiculos.service.UsuarioService;

@RestController
@RequestMapping(path = "/CadastroUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	private UsuarioService usuarioService;
	private ApplicationEventPublisher publisher;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService, ApplicationEventPublisher publisher) {
		this.usuarioService = usuarioService;
		this.publisher = publisher;
	}

	@GetMapping
	public List<Usuario> listUsuario(){
		return usuarioService.listUsuario();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getIdUsuario(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(usuarioService.getUsuario(id));
	}	
	
	@PostMapping
	public ResponseEntity<Usuario> saveUsuario(@Validated @RequestBody Usuario usuario, HttpServletResponse response){
		Usuario u = usuarioService.saveUsuario(usuario);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, u.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
		return ResponseEntity.ok(usuarioService.updateUsuario(id, usuario));
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUsuario(@PathVariable Long id) {
		usuarioService.deleteUsuario(id);
	}
}