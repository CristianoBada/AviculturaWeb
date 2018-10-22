package com.herokuapp.cristcc2.repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Convidado;
import com.herokuapp.cristcc2.Models.Evento;


public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByRg(String rg);
}
