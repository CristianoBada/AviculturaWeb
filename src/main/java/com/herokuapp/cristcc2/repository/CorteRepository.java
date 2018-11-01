package com.herokuapp.cristcc2.repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Corte;


public interface CorteRepository extends CrudRepository<Corte, String>{
	Corte findByCodigoCorte(Long codigoCorte);
}
