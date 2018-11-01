package com.herokuapp.cristcc2.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Racao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoRacao;
	
	@NotEmpty
	@Size(min=1, max=20)
	private String tipoRacao;
	
	@NotNull
    private Integer quantidade;
    
	@NotEmpty
    @Size(min=10, max=10)
    private String dataEntrada;
	
	 @ManyToOne
	 private Postura granjaPostura;
	 
	 @ManyToOne
	 private Corte granjaCorte;
	 
	 public Racao( ) {
		 this.tipoRacao = "";
		 this.quantidade = 0;
	 }


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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public String getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	
}
