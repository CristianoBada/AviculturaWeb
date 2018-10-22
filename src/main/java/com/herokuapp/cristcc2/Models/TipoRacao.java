package com.herokuapp.cristcc2.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoRacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String nomeRacao;
	
	private String descricaoRacao;

	public String getNomeRacao() {
		return nomeRacao;
	}

	public void setNomeRacao(String nomeRacao) {
		this.nomeRacao = nomeRacao;
	}

	public String getDescricaoRacao() {
		return descricaoRacao;
	}

	public void setDescricaoRacao(String descricaoRacao) {
		this.descricaoRacao = descricaoRacao;
	}
}
