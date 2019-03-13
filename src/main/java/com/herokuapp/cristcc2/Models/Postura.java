package com.herokuapp.cristcc2.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Postura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoPostura;
	
	@NotNull
	private Integer quantidade;
	
	@Size(min=0, max=100)
    private String comentario;
    
    @NotNull
    private Integer maximoAves;
    
    @NotEmpty
    @Size(min=10, max=10)
    private String dataentrada;
    
    @NotEmpty
    @Size(min=10, max=10)
    private String datasaida;
    
    @NotEmpty
    private String tipoAve;

	public long getCodigoPostura() {
		return codigoPostura;
	}

	public void setCodigoPostura(long codigoPostura) {
		this.codigoPostura = codigoPostura;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getMaximoAves() {
		return maximoAves;
	}

	public void setMaximoAves(Integer maximoAves) {
		this.maximoAves = maximoAves;
	}

	public String getDataentrada() {
		return dataentrada;
	}

	public void setDataentrada(String dataentrada) {
		this.dataentrada = dataentrada;
	}

	public String getDatasaida() {
		return datasaida;
	}

	public void setDatasaida(String datasaida) {
		this.datasaida = datasaida;
	}

	public String getTipoAve() {
		return tipoAve;
	}

	public void setTipoAve(String tipoAve) {
		this.tipoAve = tipoAve;
	}
}
