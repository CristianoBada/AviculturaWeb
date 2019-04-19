package com.herokuapp.cristcc2.Repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Entidades.Ovos;

public interface OvosRepository extends CrudRepository<Ovos, String>{
	Ovos findByCodigo(long codigo);
}
