package com.herokuapp.cristcc2.Repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Entidades.Racao;

public interface RacaoRepository extends CrudRepository<Racao, String>{
	Racao findByCodigo(Long codigo);
}
