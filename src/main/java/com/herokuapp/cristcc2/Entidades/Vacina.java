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
public class Vacina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	@Size(min = 10, max = 10)
	private String data2;
	
	@NotNull
    private String data;

	@NotEmpty
	@Size(min = 1, max = 20)
	private String tipo;

	@Size(min = 0, max = 100)
	private String observacao;
	
	private long corte;
	
	private long postura;
	
	public  Vacina() {
		super();
		postura = -1;
		corte = -1;
	}
	
	public long getPostura() {
		return postura;
	}

	public void setPostura(long postura) {
		this.postura = postura;
	}

	public long getCorte() {
		return corte;
	}

	public void setCorte(long corte) {
		this.corte = corte;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
