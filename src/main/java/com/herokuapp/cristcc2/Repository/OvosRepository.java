package com.herokuapp.cristcc2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herokuapp.cristcc2.Entidades.Ovos;

public interface OvosRepository extends JpaRepository<Ovos, Long>{
	Ovos findByCodigo(long codigo);
	
	List<Ovos> findByDataBetween(String data1, String data2);
	
	List<Ovos> findByTipoave(String tipoave);
	
	List<Ovos> findByDataBetweenAndTipoave(String data1, String data2, String tipoave);
}
