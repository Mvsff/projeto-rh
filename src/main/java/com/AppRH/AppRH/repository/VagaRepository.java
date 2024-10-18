package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.AppRH.AppRH.models.Vagas;


//Busca o codigo e o nome no repository
public interface VagaRepository extends CrudRepository<Vagas, String>{
	Vagas findByCodigo(long codigo);
	List<Vagas> findByNome(String nome);
	

}
