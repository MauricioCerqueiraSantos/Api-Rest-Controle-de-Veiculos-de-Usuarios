package br.com.controle.veiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controle.veiculos.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{
		
}
