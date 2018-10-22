package com.herokuapp.cristcc2.repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	Usuario findByLogin(String login);
}
