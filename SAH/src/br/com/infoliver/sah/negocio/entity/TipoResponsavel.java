package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Tipo Responsável", genero = GeneroEntity.Masculino)
public class TipoResponsavel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer sequencial;
	private String descricao;

	public TipoResponsavel() {
	}

	public TipoResponsavel(String descricao) {
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