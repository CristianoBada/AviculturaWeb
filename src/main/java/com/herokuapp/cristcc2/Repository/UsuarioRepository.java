package com.herokuapp.cristcc2.Repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	Usuario findByLogin(String login);
}
