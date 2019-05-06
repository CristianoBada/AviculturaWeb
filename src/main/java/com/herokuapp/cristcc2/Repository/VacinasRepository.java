package com.herokuapp.cristcc2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herokuapp.cristcc2.Entidades.Vacina;

public interface VacinasRepository extends JpaRepository<Vacina, Long> {
	List<Vacina> findByCodigo(Long codigo);

	List<Vacina> findByDataBetween(String data1, String data2);

	List<Vacina> findByTipo(String tipo);

	List<Vacina> findByCodigoAndDataBetween(Long codigo, String data1, String data2);

	List<Vacina> findByCodigoAndTipo(Long codigo, String tipo);

	List<Vacina> findByCodigoAndDataBetweenAndTipo(Long codigo, String data1, String data2, String tipo);

	List<Vacina> findByDataBetweenAndTipo(String data1, String data2, String tipo);
}
