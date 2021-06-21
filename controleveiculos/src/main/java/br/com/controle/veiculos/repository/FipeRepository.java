package br.com.controle.veiculos.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controle.veiculos.consumer.Fipe;

@Repository
@FeignClient(value = "jplaceholder", url = "https://jsonplaceholder.typicode.com/")
public interface FipeRepository extends JpaRepository<Fipe, Long> {
	
	public Fipe getByFipe(String valor);


}
