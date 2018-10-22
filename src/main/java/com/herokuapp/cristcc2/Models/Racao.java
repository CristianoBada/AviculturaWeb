package com.herokuapp.cristcc2.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Racao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoRacao;
	
	@NotNull
	private String tipoRacao;
	
	@NotNull
    private String quantidade;
    
	@NotNull
    private String dataEntrada;

	public long getCodigoRacao() {
		return codigoRacao;
	}

	public void setCodigoRacao(long codigoRacao) {
		this.codigoRacao = codigoRacao;
	}

	public String getTipoRacao() {
		return tipoRacao;
	}

	public void setTipoRacao(String tipoRacao) {
		this.tipoRacao = tipoRacao;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
}
