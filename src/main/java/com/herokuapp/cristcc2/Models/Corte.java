package com.herokuapp.cristcc2.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Corte implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoCorte;
	
	@NotNull
    private String quantidadeAves;
    
    private String mortalidade;
    
    private String comentario;
    
    @NotNull
    private String maximo;
    
    @NotNull
    private String dataEntrada;
    
    private String dataSaida;
    
    @NotNull
    private String tipoAve;

	public long getCodigoCorte() {
		return codigoCorte;
	}

	public void setCodigoCorte(long codigoCorte) {
		this.codigoCorte = codigoCorte;
	}

	public String getQuantidadeAves() {
		return quantidadeAves;
	}

	public void setQuantidadeAves(String quantidadeAves) {
		this.quantidadeAves = quantidadeAves;
	}

	public String getMortalidade() {
		return mortalidade;
	}

	public void setMortalidade(String mortalidade) {
		this.mortalidade = mortalidade;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getMaximo() {
		return maximo;
	}

	public void setMaximo(String maximo) {
		this.maximo = maximo;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getTipoAve() {
		return tipoAve;
	}

	public void setTipoAve(String tipoAve) {
		this.tipoAve = tipoAve;
	}
}
