package com.AppRH.AppRH.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.AppRH.AppRH.models.Candidato;
import com.AppRH.AppRH.models.Vagas;

public interface CandidatoRepository extends CrudRepository<Candidato, String> {
	
	Iterable<Candidato>findByVagas(Vagas vaga);
	
	Candidato findByRg (String rg);
	
	Candidato findById(long id);
	
	List<Candidato>findByNomeCandidato(String nomeCandidato);

}
