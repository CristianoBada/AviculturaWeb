package com.herokuapp.cristcc2.repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Incubatorio;

public interface IncubatorioRepository extends CrudRepository<Incubatorio, String>{
	
	Incubatorio findByCodigo(long codigo);
}
