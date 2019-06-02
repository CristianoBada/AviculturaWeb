package com.herokuapp.cristcc2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herokuapp.cristcc2.Entidades.Corte;


public interface CorteRepository extends JpaRepository<Corte, String>{
	Corte findByCodigo(Long codigo);
	
	List<Corte> findByEntradaBetween(String data1, String data2);
	
	List<Corte> findBySaidaBetween(String data1, String data2);
	
	List<Corte> findByTipoave(String tipoave);
	
	List<Corte> findByEntradaBetweenAndSaidaBetween(String data1, String data2, String data3, String data4);
	
	List<Corte> findByEntradaBetweenAndSaidaBetweenAndTipoave(String data1, String data2, String data3, String data4, String tipoave);
	
	List<Corte> findByEntradaBetweenAndTipoave(String data1, String data2, String tipoave);
	
	List<Corte> findBySaidaBetweenAndTipoave(String data1, String data2, String tipoave);
}
