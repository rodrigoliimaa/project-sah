package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;
import br.com.infoliver.sah.negocio.entity.Paciente.AlterarPaciente;
import br.com.infoliver.sah.negocio.entity.Paciente.InserirPaciente;

@EntityValidador(entidade = "Raça", genero = GeneroEntity.Feminino)
public class Raca implements Serializable {
	private static final long serialVersionUID = 1L;

    @NotNull(message="O código da RAÇA é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	private Integer sequencial;

    private String descricao;

	public Raca() {
	}

	public Raca(String descricao) {
		this.descricao=descricao;
	}

	public Raca(Integer sequencial,String descricao) {
		this.sequencial=sequencial;
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