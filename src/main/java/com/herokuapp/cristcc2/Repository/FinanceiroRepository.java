package com.herokuapp.cristcc2.Repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Entidades.Financeiro;

public interface FinanceiroRepository extends CrudRepository<Financeiro, String>{
	Financeiro findByCodigo(Long codigo);
}
