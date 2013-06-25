package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Motivo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//-------------------------------
	public interface AlterarMotivo {}
	public interface ExcluirMotivo {}
	public interface InserirMotivo {}
	//-------------------------------
	
	@NotNull(message = "O código do MOTIVO é obrigatório", groups = {AlterarMotivo.class, ExcluirMotivo.class})
	private Integer sequencial;
	
	@NotNull(message = "O campo DESCRIÇÃO é obrigatório", groups = {AlterarMotivo.class, InserirMotivo.class})
	@NotBlank(message = "O campo DESCRIÇÃO não pode ser vazio", groups = {AlterarMotivo.class, InserirMotivo.class})
	private String descricao;
	
	public Motivo() {

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
