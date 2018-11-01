package com.herokuapp.cristcc2.Models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Incubatorio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoIncubatorio;
	
	@NotEmpty
	private String loteOvos;
	
	@NotNull
    private Integer temperatura;
	
    private Integer umidade;
    
    @NotNull
    private Integer tempoChocar;
    
    @NotNull
    private Date dataInicio;
    
    private Integer mortalidade;
    
    @NotEmpty
    private String tipoAve;
    
    @OneToMany
	private List<Ovos> lotesOvos;
    
	public List<Ovos> getLotesOvos() {
		return lotesOvos;
	}

	public void setLotesOvos(List<Ovos> lotesOvos) {
		this.lotesOvos = lotesOvos;
	}

	public long getCodigoIncubatorio() {
		return codigoIncubatorio;
	}

	public void setCodigoIncubatorio(long codigoIncubatorio) {
		this.codigoIncubatorio = codigoIncubatorio;
	}

	public String getLoteOvos() {
		return loteOvos;
	}

	public void setLoteOvos(String loteOvos) {
		this.loteOvos = loteOvos;
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

	public Integer getTempoChocar() {
		return tempoChocar;
	}

	public void setTempoChocar(Integer tempoChocar) {
		this.tempoChocar = tempoChocar;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getMortalidade() {
		return mortalidade;
	}

	public void setMortalidade(Integer mortalidade) {
		this.mortalidade = mortalidade;
	}

	public String getTipoAve() {
		return tipoAve;
	}

	public void setTipoAve(String tipoAve) {
		this.tipoAve = tipoAve;
	}
}
