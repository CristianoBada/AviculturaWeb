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
public class Racao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotEmpty
	@Size(min=1, max=20)
	private String tiporacao;
	
	@NotNull
    private Integer quantidade;
	
	private Integer codigopostura;
	 
	private Integer codigocorte;
	
	@NotNull
    private String data;
	
	@Size(min=10, max=10)
	private String data2;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getTiporacao() {
		return tiporacao;
	}

	public void setTiporacao(String tiporacao) {
		this.tiporacao = tiporacao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getCodigopostura() {
		return codigopostura;
	}

	public void setCodigopostura(Integer codigopostura) {
		this.codigopostura = codigopostura;
	}

	public Integer getCodigocorte() {
		return codigocorte;
	}

	public void setCodigocorte(Integer codigocorte) {
		this.codigocorte = codigocorte;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}
}
