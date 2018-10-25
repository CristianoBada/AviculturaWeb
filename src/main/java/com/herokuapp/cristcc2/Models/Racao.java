package com.herokuapp.cristcc2.Models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Racao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoRacao;
	
	@NotEmpty
	private String tipoRacao;
	
	@NotEmpty
    private String quantidade;
    
	@NotEmpty
    private String dataEntrada;
	
	 @ManyToOne
	 private Postura granjaPostura;
	 
	 @ManyToOne
	 private Corte granjaCorte;


	public Postura getGranjaPostura() {
		return granjaPostura;
	}

	public void setGranjaPostura(Postura granjaPostura) {
		this.granjaPostura = granjaPostura;
	}

	public Corte getGranjaCorte() {
		return granjaCorte;
	}

	public void setGranjaCorte(Corte granjaCorte) {
		this.granjaCorte = granjaCorte;
	}

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
