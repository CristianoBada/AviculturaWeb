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
public class Corte implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotNull
    private Integer quantidade;
    
    private Integer mortalidade;
    
    @Size(min=0, max=100)
    private String observacao;
    
    @NotNull
    private Integer maximo;
    
    @NotEmpty
    @Size(min=10, max=10)
    private String entrada;
    
    @Size(min=10, max=10)
    private String saida;
    
    @NotEmpty
    @Size(min=1, max=15)
    private String tipoave;

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getMortalidade() {
		return mortalidade;
	}

	public void setMortalidade(Integer mortalidade) {
		this.mortalidade = mortalidade;
	}

	public Integer getMaximo() {
		return maximo;
	}

	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public String getTipoave() {
		return tipoave;
	}

	public void setTipoave(String tipoave) {
		this.tipoave = tipoave;
	}
}
