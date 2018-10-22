package com.herokuapp.cristcc2.repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Ovos;

public interface OvosRepository extends CrudRepository<Ovos, String>{
	Ovos findByCodigo(long codigo);
}
