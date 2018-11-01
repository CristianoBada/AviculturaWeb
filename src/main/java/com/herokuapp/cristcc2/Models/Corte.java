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
import javax.validation.constraints.Size;

@Entity
public class Corte implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigoCorte;
	
	@NotNull
    private Integer quantidadeAves;
    
    private Integer mortalidade;
    
    @Size(min=0, max=100)
    private String comentario;
    
    @NotNull
    private Integer maximo;
    
    @NotEmpty
    @Size(min=10, max=10)
    private String dataEntrada;
    
    private Date dataSaida;
    
    @NotEmpty
    @Size(min=1, max=15)
    private String tipoAve;
    
    @OneToMany
	 private List<Racao> loteRacao;
    
	public Integer getMaximo() {
		return maximo;
	}

	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}

	
	
	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public List<Racao> getLoteRacao() {
		return loteRacao;
	}

	public void setLoteRacao(List<Racao> loteRacao) {
		this.loteRacao = loteRacao;
	}

	public long getCodigoCorte() {
		return codigoCorte;
	}

	public void setCodigoCorte(long codigoCorte) {
		this.codigoCorte = codigoCorte;
	}

	public Integer getQuantidadeAves() {
		return quantidadeAves;
	}

	public void setQuantidadeAves(Integer quantidadeAves) {
		this.quantidadeAves = quantidadeAves;
	}

	public Integer getMortalidade() {
		return mortalidade;
	}

	public void setMortalidade(Integer mortalidade) {
		this.mortalidade = mortalidade;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public String getTipoAve() {
		return tipoAve;
	}

	public void setTipoAve(String tipoAve) {
		this.tipoAve = tipoAve;
	}
}
