package com.herokuapp.cristcc2.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Incubatorio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	@NotEmpty
	private String loteovos;

	@NotNull
	private Integer temperatura;

	private Integer umidade;

	@NotNull
	private Integer tempo;

	@NotEmpty
	@Size(min = 10, max = 10)
	private String inicio;

	private Integer mortalidade;

	@NotEmpty
	@Size(min = 1, max = 20)
	private String tipoave;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getLoteovos() {
		return loteovos;
	}

	public void setLoteovos(String loteovos) {
		this.loteovos = loteovos;
	}

	public Integer getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Integer temperatura) {
		this.temperatura = temperatura;
	}

	public Integer getUmidade() {
		return umidade;
	}

	public void setUmidade(Integer umidade) {
		this.umidade = umidade;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public Integer getMortalidade() {
		return mortalidade;
	}

	public void setMortalidade(Integer mortalidade) {
		this.mortalidade = mortalidade;
	}

	public String getTipoave() {
		return tipoave;
	}

	public void setTipoave(String tipoave) {
		this.tipoave = tipoave;
	}
}
