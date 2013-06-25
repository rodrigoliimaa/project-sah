package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;
import br.com.infoliver.sah.negocio.entity.Paciente.AlterarPaciente;
import br.com.infoliver.sah.negocio.entity.Paciente.InserirPaciente;

@EntityValidador(entidade = "Escolaridade", genero = GeneroEntity.Feminino)
public class Escolaridade implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message="O código da ESCOLARIDADE é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	private Integer sequencial;

	private String descricao;

	public Escolaridade() {
	}

	public Escolaridade(String descricao) {
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