package com.herokuapp.cristcc2.Models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.herokuapp.cristcc2.Json.JsonDateSerializer;

@Entity
public class Postura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoPostura;
	
	@NotNull
	private Integer quantidade;
	
    private String comentario;
    
    @NotNull
    private Integer maximoAves;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:ii:ss")
    private Date dataentrada;
    
    @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:ii:ss")
    private Date datasaida;
    
    @NotEmpty
    private String tipoAve;
    
    @OneToMany
	private List<Ovos> lotesOvos;
    
	public List<Ovos> getLotesOvos() {
		return lotesOvos;
	}
	
	 @OneToMany
	 private List<Racao> loteRacao;

	public List<Racao> getLoteRacao() {
		return loteRacao;
	}

	public void setLoteRacao(List<Racao> loteRacao) {
		this.loteRacao = loteRacao;
	}

	public void setLotesOvos(List<Ovos> lotesOvos) {
		this.lotesOvos = lotesOvos;
	}

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

	@JsonSerialize(using=JsonDateSerializer.class) 
	public Date getDataentrada() {
		return dataentrada;
	}

	public void setDataentrada(Date dataentrada) {
		this.dataentrada = dataentrada;
	}

	@JsonSerialize(using=JsonDateSerializer.class) 
	public Date getDatasaida() {
		return datasaida;
	}

	public void setDatasaida(Date datasaida) {
		this.datasaida = datasaida;
	}

	public String getTipoAve() {
		return tipoAve;
	}

	public void setTipoAve(String tipoAve) {
		this.tipoAve = tipoAve;
	}
}
