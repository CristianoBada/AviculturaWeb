package com.herokuapp.cristcc2.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Ovos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotNull
	private String quantidade;
	
    private String qualidade;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String data;
    
    @NotNull
    private String incubacao = "false";
    
    @NotNull
    private String lote;
    
    @ManyToOne
	private Postura postura;
    
    @ManyToOne
	private Postura incubatorio;
    
	public Postura getPostura() {
		return postura;
	}

	public void setPostura(Postura postura) {
		this.postura = postura;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getQualidade() {
		return qualidade;
	}

	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIncubacao() {
		return incubacao;
	}

	public void setIncubacao(String incubacao) {
		this.incubacao = incubacao;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}
    
}
