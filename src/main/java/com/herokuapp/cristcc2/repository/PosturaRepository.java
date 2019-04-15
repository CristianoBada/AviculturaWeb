package com.herokuapp.cristcc2.repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Postura;

public interface PosturaRepository extends CrudRepository<Postura, String>{
	Postura findByCodigo(long codigo);
}
