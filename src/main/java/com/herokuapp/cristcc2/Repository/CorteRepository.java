package com.herokuapp.cristcc2.Repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Entidades.Corte;


public interface CorteRepository extends CrudRepository<Corte, String>{
	Corte findByCodigo(Long codigo);
}
