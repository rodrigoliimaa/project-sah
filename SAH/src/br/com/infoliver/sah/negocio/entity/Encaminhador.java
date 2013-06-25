package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Encaminhador", genero = GeneroEntity.Masculino)
public class Encaminhador implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer sequencial;
	private String descricao;

	public Encaminhador() {
	}

	public Encaminhador(String descricao) {
		this.descricao=descricao;
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