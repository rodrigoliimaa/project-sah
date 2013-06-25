package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;
import br.com.infoliver.sah.negocio.entity.Medico.InserirMedico;
import br.com.infoliver.sah.negocio.entity.Paciente.InserirPaciente;

@EntityValidador(entidade = "Ocupação", genero = GeneroEntity.Feminino)
public class Ocupacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "O código da OCUPAÇÃO é obrigatório", groups = {InserirPaciente.class, InserirMedico.class})
	private Integer sequencial;
	
	private String descricao;
	private String codigoOcupacao;

	public Ocupacao() {
	}

	public Ocupacao(String descricao) {
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

	public String getCodigoOcupacao() {
		return codigoOcupacao;
	}

	public void setCodigoOcupacao(String codigoOcupacao) {
		this.codigoOcupacao = codigoOcupacao;
	}
}