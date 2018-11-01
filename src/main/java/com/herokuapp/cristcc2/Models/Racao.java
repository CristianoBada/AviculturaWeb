package com.herokuapp.cristcc2.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.herokuapp.cristcc2.Json.JsonDateSerializer;

@Entity
public class Racao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoRacao;
	
	@NotEmpty
	private String tipoRacao;
	
	@NotNull
    private Integer quantidade;
    
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy")
    private Date dataEntrada;
	
	 @ManyToOne
	 private Postura granjaPostura;
	 
	 @ManyToOne
	 private Corte granjaCorte;
	 
	 public Racao( ) {
		 this.tipoRacao = "";
		 this.quantidade = 0;
		 this.dataEntrada = new Date();
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

	@JsonSerialize(using=JsonDateSerializer.class) 
	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
}
