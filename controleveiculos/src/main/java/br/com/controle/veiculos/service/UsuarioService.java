package br.com.controle.veiculos.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.veiculos.exception.EntidadeNaoEncontradoException;
import br.com.controle.veiculos.model.Usuario;
import br.com.controle.veiculos.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	//GET buscar a lista de usuario
	public List<Usuario> listUsuario() {
		return usuarioRepository.findAll();
	}
	
	//GET buscar usuario
	public Usuario getUsuario(Long id) {
		return findOrFail(id);
	}
	
	//POST Salvar o usuario e seu carro
	public Usuario saveUsuario(Usuario u) {
		u.getCarro().forEach(c -> c.setUsuario(u));
		return usuarioRepository.save(u);
	}
	
	//PUT Atualizar dados do usuario
	public Usuario updateUsuario(Long id, Usuario u) {
		Usuario updateUsuarioSalva = findOrFail(id);
		
		updateUsuarioSalva.getCarro().clear();
		
		updateUsuarioSalva.getCarro().addAll(u.getCarro());
		
		updateUsuarioSalva.getCarro().forEach(c -> c.setUsuario(updateUsuarioSalva));
		
		BeanUtils.copyProperties(u, updateUsuarioSalva, "id", "carro");
		
		return usuarioRepository.save(updateUsuarioSalva);
	}
	
	//DELETE deletar usuario
	public void deleteUsuario(Long id) {
		Usuario u = findOrFail(id);
		usuarioRepository.delete(u);
	}

	private Usuario findOrFail(Long id) {
		return usuarioRepository.findById(id).
				orElseThrow(() -> new EntidadeNaoEncontradoException("Usuario Nao Encontrado!"));
	}
}