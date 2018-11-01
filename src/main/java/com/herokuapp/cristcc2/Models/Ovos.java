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
public class Ovos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotNull
	private Integer quantidade;
	
    private String qualidade;
    
    @NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy")
    private Date data;
    
    @NotNull
    private Boolean incubacao = false;
    
    @NotEmpty
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

	public String getQualidade() {
		return qualidade;
	}

	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class) 
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Boolean getIncubacao() {
		return incubacao;
	}

	public void setIncubacao(Boolean incubacao) {
		this.incubacao = incubacao;
	}

	public Postura getIncubatorio() {
		return incubatorio;
	}

	public void setIncubatorio(Postura incubatorio) {
		this.incubatorio = incubatorio;
	}
	
	
    
}
