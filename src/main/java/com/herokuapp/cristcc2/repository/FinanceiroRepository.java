package com.herokuapp.cristcc2.repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Financeiro;

public interface FinanceiroRepository extends CrudRepository<Financeiro, String>{
	Financeiro findByCodigo(Long codigo);
}
