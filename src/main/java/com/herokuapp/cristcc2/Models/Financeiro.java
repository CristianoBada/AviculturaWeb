package com.herokuapp.cristcc2.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Financeiro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoFinanceiro;

	@NotNull
    private String nome;
    
	@NotNull
    private String valor;
    
    private String detalhe;
    
    @NotNull
    private String entrasaida;

	public long getCodigoFinanceiro() {
		return codigoFinanceiro;
	}

	public void setCodigoFinanceiro(long codigoFinanceiro) {
		this.codigoFinanceiro = codigoFinanceiro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getEntrasaida() {
		return entrasaida;
	}

	public void setEntrasaida(String entrasaida) {
		this.entrasaida = entrasaida;
	}
}
