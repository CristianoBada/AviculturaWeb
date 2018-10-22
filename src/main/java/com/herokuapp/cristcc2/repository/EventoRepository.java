package com.herokuapp.cristcc2.repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Evento;


public interface EventoRepository extends CrudRepository<Evento, String>{
	Evento findByCodigo(long codigo);
}
