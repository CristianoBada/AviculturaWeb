package com.herokuapp.cristcc2.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class TipoAve implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String nomeAve;
	
	@NotNull
	private Integer tempoChocagem;

	public String getNomeAve() {
		return nomeAve;
	}

	public void setNomeAve(String nomeAve) {
		this.nomeAve = nomeAve;
	}

	public Integer getTempoChocagem() {
		return tempoChocagem;
	}

	public void setTempoChocagem(Integer tempoChocagem) {
		this.tempoChocagem = tempoChocagem;
	}

	
}
