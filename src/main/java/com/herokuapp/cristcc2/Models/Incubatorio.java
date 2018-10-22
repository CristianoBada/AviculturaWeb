package com.herokuapp.cristcc2.Models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Incubatorio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoIncubatorio;
	
	@NotNull
	private String loteOvos;
	
	@NotNull
    private String temperatura;
	
    private String umidade;
    
    @NotNull
    private String tempoChocar;
    
    @NotNull
    private String dataInicio;
    
    private String mortalidade;
    
    @NotNull
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

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getUmidade() {
		return umidade;
	}

	public void setUmidade(String umidade) {
		this.umidade = umidade;
	}

	public String getTempoChocar() {
		return tempoChocar;
	}

	public void setTempoChocar(String tempoChocar) {
		this.tempoChocar = tempoChocar;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getMortalidade() {
		return mortalidade;
	}

	public void setMortalidade(String mortalidade) {
		this.mortalidade = mortalidade;
	}

	public String getTipoAve() {
		return tipoAve;
	}

	public void setTipoAve(String tipoAve) {
		this.tipoAve = tipoAve;
	}
}
