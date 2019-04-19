package com.herokuapp.cristcc2.Entidades;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@Size(min=3, max=20)
	private String login;
	
	@Size(min=0, max=50)
	private String nomeCompleto;
	
	@NotEmpty
	@Size(min=1, max=200)
	private String senha;
	
	@Size(min=1, max=200)
	private String senha2;

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
