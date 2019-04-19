package com.herokuapp.cristcc2.Repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Entidades.Incubatorio;

public interface IncubatorioRepository extends CrudRepository<Incubatorio, String>{
	
	Incubatorio findByCodigo(long codigo);
}
