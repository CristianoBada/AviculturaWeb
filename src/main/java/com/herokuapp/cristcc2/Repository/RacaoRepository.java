package com.herokuapp.cristcc2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herokuapp.cristcc2.Entidades.Racao;

public interface RacaoRepository extends JpaRepository<Racao, String>{
	Racao findByCodigo(Long codigo);
	
	List<Racao> findByTiporacao(String tipo);
	
	List<Racao> findByDataBetween(String data, String data2);
	
	List<Racao> findByTiporacaoAndDataBetween(String tipo, String data, String data2);
	
	List<Racao> findByPostura(long postura);
	
	List<Racao> findByCorte(long corte);
}
