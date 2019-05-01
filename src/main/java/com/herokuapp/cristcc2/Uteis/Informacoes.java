package com.herokuapp.cristcc2.Uteis;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Informacoes {

	public String usuarioAtual() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		    return ((UserDetails)principal).getUsername();
		} else {
		    return principal.toString();
		}
	}
}
