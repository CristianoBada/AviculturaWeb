package com.herokuapp.cristcc2.repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Racao;

public interface RacaoRepository extends CrudRepository<Racao, String>{
	Racao findByCodigo(Long codigo);
}
