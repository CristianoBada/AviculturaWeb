package com.herokuapp.cristcc2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herokuapp.cristcc2.Entidades.Financeiro;

public interface FinanceiroRepository extends JpaRepository<Financeiro, String>{
	Financeiro findByCodigo(Long codigo);
	
	List<Financeiro> findByNome(String nome);
	
	List<Financeiro> findByEntrasaida(String entradasaida);
	
	List<Financeiro> findByNomeAndEntrasaida(String nome, String entradasaida);
	
	List<Financeiro> findByDataBetween(String data1, String data2);
	
	List<Financeiro> findByDataBetweenAndEntrasaida(String data1, String data2, String entradasaida);
	
	List<Financeiro> findByNomeAndDataBetween(String nome, String data1, String data2);
	
	List<Financeiro> findByNomeAndEntrasaidaAndDataBetween(String nome, String entradasaida, String data1, String data2);
}
