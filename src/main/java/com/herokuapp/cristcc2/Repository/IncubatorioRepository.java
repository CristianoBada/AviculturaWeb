package com.herokuapp.cristcc2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herokuapp.cristcc2.Entidades.Incubatorio;

public interface IncubatorioRepository extends JpaRepository<Incubatorio, String>{
	
	Incubatorio findByCodigo(long codigo);
	
	List<Incubatorio> findByInicioBetween(String data1, String data2);
	
	List<Incubatorio> findByTipoave(String tipoave);
	
	List<Incubatorio> findByInicioBetweenAndTipoave(String data1, String data2, String tipoave);
}
