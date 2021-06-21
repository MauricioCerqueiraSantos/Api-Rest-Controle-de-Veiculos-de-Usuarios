package br.com.controle.veiculos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.controle.veiculos.exception.EntidadeNaoEncontradoException;
import br.com.controle.veiculos.model.Carro;
import br.com.controle.veiculos.repository.CarroRepository;

@Service
public class CarroService {
	
	private CarroRepository carroRepository;
	
	@Autowired
	public CarroService(CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}	
	
	//GET buscar a lista de carros
	public List<Carro> listCarro(){
		return carroRepository.findAll();
	}
	
	//GET buscar carro
	public Carro getCarro(Long id) {
		return findOrFail(id);
	}
	
	//POST salvar carro
	@PostMapping
	public Carro saveCarro(@RequestBody Carro c) {			
		return carroRepository.save(c);
	}	
	
	//PUT Atualizar os dados do Carro
	public Carro updateCarro(Long id, Carro c) {
		Carro updateCarroSalva = findOrFail(id);
		return carroRepository.save(updateCarroSalva);		
	}	
	
	//DELETE deletar usuario
		public void deleteCarro(Long id) {
			Carro c = findOrFail(id);
			carroRepository.delete(c);
		}

		private Carro findOrFail(Long id) {
			return carroRepository.findById(id).
					orElseThrow(() -> new EntidadeNaoEncontradoException("Carro Nao Encontrado!"));
		}
}