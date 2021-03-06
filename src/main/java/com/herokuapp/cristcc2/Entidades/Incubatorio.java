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

	@NotNull
	private Double temperatura;

	private Integer umidade = 0;

	private Integer tempo = 0;

	@NotEmpty
	@Size(min = 10, max = 10)
	private String inicio;
	
	@Size(min = 10, max = 10)
	private String inicio2;

	private Integer mortalidade;

	@Size(min = 1, max = 20)
	private String tipoave;
	
	private long ovos;

	public String getInicio2() {
		return inicio2;
	}

	public void setInicio2(String inicio2) {
		this.inicio2 = inicio2;
	}

	public long getOvos() {
		return ovos;
	}

	public void setOvos(long ovos) {
		this.ovos = ovos;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
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
