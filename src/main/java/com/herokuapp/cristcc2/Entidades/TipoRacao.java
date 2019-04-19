package com.herokuapp.cristcc2.Entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class TipoRacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Size(min=1, max=20)
	private String nomeRacao;
	
	@Size(min=0, max=100)
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
