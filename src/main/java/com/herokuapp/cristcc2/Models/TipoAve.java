package com.herokuapp.cristcc2.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoAve implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String nomeAve;

	public String getNomeAve() {
		return nomeAve;
	}

	public void setNomeAve(String nomeAve) {
		this.nomeAve = nomeAve;
	}
}
