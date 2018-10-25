package com.herokuapp.cristcc2.Models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Postura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoPostura;
	
	@NotEmpty
	private String quantidade;
	
    private String comentario;
    
    @NotEmpty
    private String maximoAves;
    
    @NotEmpty
    private String dataentrada;
    
    private String datasaida;
    
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

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getMaximoAves() {
		return maximoAves;
	}

	public void setMaximoAves(String maximoAves) {
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
