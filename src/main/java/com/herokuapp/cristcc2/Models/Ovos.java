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
public class Ovos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotNull
	private Integer quantidade;
	
	 @Size(min=1, max=15)
    private String qualidade;
    
    @NotEmpty
    @Size(min=10, max=10)
    private String data;
    
    @NotNull
    private Boolean incubacao = false;
    
    @NotEmpty
    @Size(min=1, max=15)
    private String lote;
    
    @NotEmpty
    private String tipoAve;
    
	private Postura postura;
    
	private Incubatorio incubatorio;
    
    
	public String getTipoAve() {
		return tipoAve;
	}

	public void setTipoAve(String tipoAve) {
		this.tipoAve = tipoAve;
	}

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
		
	public String getData() {
		return data;
	}

	public void setData(String data) {
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

	public Incubatorio getIncubatorio() {
		return incubatorio;
	}

	public void setIncubatorio(Incubatorio incubatorio) {
		this.incubatorio = incubatorio;
	}
	
	
    
}
