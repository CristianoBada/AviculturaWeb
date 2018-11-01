package com.herokuapp.cristcc2.repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Vacina;

public interface VacinasRepository extends CrudRepository<Vacina, String>{
	Vacina findByCodigoVacina(Long codigoVacina);
}
