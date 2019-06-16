package com.herokuapp.cristcc2.Entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Produtos implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Size(min=1, max=20)
	private String nomeProduto;
	
	private String tipounidade;

	public String getTipounidade() {
		return tipounidade;
	}

	public void setTipounidade(String tipounidade) {
		this.tipounidade = tipounidade;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
}
