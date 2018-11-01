package com.herokuapp.cristcc2.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Vacina implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoVacina;
	
	@NotEmpty
    @Size(min=10, max=10)
    private String dataTratamento;
	
	@NotEmpty
	@Size(min=1, max=20)
    private String tipoTratamento;
	
	@Size(min=0, max=100)
    private String detalhe;

	public long getCodigoVacina() {
		return codigoVacina;
	}

	public void setCodigoVacina(long codigoVacina) {
		this.codigoVacina = codigoVacina;
	}


	public String getDataTratamento() {
		return dataTratamento;
	}

	public void setDataTratamento(String dataTratamento) {
		this.dataTratamento = dataTratamento;
	}

	public String getTipoTratamento() {
		return tipoTratamento;
	}

	public void setTipoTratamento(String tipoTratamento) {
		this.tipoTratamento = tipoTratamento;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
}
