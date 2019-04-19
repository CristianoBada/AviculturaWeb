package com.herokuapp.cristcc2.Repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Entidades.Postura;

public interface PosturaRepository extends CrudRepository<Postura, String>{
	Postura findByCodigo(long codigo);
}
