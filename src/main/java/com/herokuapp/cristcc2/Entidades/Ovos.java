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
    @Size(min=1, max=1)
    private String incubacao = "F";
    
    @NotEmpty
    @Size(min=1, max=15)
    private String lote;
    
    @NotEmpty
    private String tipoave;
    
	public String getTipoave() {
		return tipoave;
	}

	public void setTipoave(String tipoave) {
		this.tipoave = tipoave;
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

	public String getIncubacao() {
		return incubacao;
	}

	public void setIncubacao(String incubacao) {
		this.incubacao = incubacao;
	}
}