package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Grupo Permissão", genero = GeneroEntity.Masculino)
public class GrupoPermissao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static interface InserirGrupoPermissao{}
	public static interface ExcluirGrupoPermissao{}

	@NotNull(message = "O código do GRUPO é obrigatório.", groups = {InserirGrupoPermissao.class, ExcluirGrupoPermissao.class })
	@NotBlank(message = "O código do GRUPO é obrigatório.", groups = {InserirGrupoPermissao.class, ExcluirGrupoPermissao.class })
	private Integer sequencialGrupo;

	@NotNull(message = "O código da PERMISSÃO é obrigatório.", groups = {InserirGrupoPermissao.class, ExcluirGrupoPermissao.class })
	@NotBlank(message = "O código da PERMISSÃO é obrigatório.", groups = {InserirGrupoPermissao.class, ExcluirGrupoPermissao.class })
	private Integer sequencialPermissao;

	public GrupoPermissao() {
	}

	public Integer getSequencialGrupo() {
		return sequencialGrupo;
	}

	public void setSequencialGrupo(Integer sequencialGrupo) {
		this.sequencialGrupo = sequencialGrupo;
	}

	public Integer getSequencialPermissao() {
		return sequencialPermissao;
	}

	public void setSequencialPermissao(Integer sequencialPermissao) {
		this.sequencialPermissao = sequencialPermissao;
	}
}