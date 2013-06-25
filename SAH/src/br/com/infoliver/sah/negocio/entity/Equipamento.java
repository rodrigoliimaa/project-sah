package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

public class Equipamento implements Serializable {
	
	private Integer sequencial;
	
	private String descricao;
	
	public Equipamento() {

	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
