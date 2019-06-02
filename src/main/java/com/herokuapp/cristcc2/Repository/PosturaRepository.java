package com.herokuapp.cristcc2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.herokuapp.cristcc2.Entidades.Postura;

public interface PosturaRepository extends JpaRepository<Postura, String>{
	
	Postura findByCodigo(long codigo);
	
	List<Postura> findByEntradaBetween(String data1, String data2);
	
	List<Postura> findBySaidaBetween(String data1, String data2);
	
	List<Postura> findByTipoave(String tipoave);
	
	List<Postura> findByCodigoAndEntradaBetween(long codigo, String data1, String data2);
	
	List<Postura> findByCodigoAndSaidaBetween(long codigo, String data1, String data2);
	
	List<Postura> findByCodigoAndTipoave(long codigo, String tipoave);
	
	List<Postura> findByCodigoAndEntradaBetweenAndSaidaBetween(long codigo, String data1, String data2, String data3, String data4);
	
	List<Postura> findByCodigoAndEntradaBetweenAndTipoave(long codigo, String data1, String data2, String tipoave);
	
	List<Postura> findByCodigoAndSaidaBetweenAndTipoave(long codigo, String data1, String data2, String tipoave);
	
	List<Postura> findByCodigoAndEntradaBetweenAndSaidaBetweenAndTipoave(long codigo, String data1, String data2, String data3, String data4, String tipoave);
	
	List<Postura> findByEntradaBetweenAndSaidaBetween(String data1, String data2, String data3, String data4);
	
	List<Postura> findByEntradaBetweenAndSaidaBetweenAndTipoave(String data1, String data2, String data3, String data4, String tipoave);
	
	List<Postura> findByEntradaBetweenAndTipoave(String data1, String data2, String tipoave);
	
	List<Postura> findBySaidaBetweenAndTipoave(String data1, String data2, String tipoave);
}
