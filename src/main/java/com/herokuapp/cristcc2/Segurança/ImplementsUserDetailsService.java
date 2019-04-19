package com.herokuapp.cristcc2.Segurança;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.herokuapp.cristcc2.Entidades.Usuario;
import com.herokuapp.cristcc2.Repository.UsuarioRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository ur;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = ur.findByLogin(login);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return usuario;
	}

}
