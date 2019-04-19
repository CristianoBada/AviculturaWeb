package com.herokuapp.cristcc2.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Entidades.Vacina;

public interface VacinasRepository extends CrudRepository<Vacina, Long>{
	 List<Vacina> findByCodigo(Long codigo);
	
	 List<Vacina> findByData(String data);
	 
	 List<Vacina> findByTipo(String tipo);
	 
	 List<Vacina> findByCodigoAndData(Long codigo, String data);
	 
	 List<Vacina> findByCodigoAndTipo(Long codigo, String tipo);
	 
	 List<Vacina> findByCodigoAndDataAndTipo(Long codigo, String data, String tipo);
	 
	 List<Vacina> findByDataAndTipo(String data, String tipo);
}
