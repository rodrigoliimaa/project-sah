package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Parametro", genero = GeneroEntity.Masculino)
public class Parametro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer sequencial;

	private String chave;

	private String valor;

	public Parametro() {
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}