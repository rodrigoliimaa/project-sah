package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Grupo Usuário", genero = GeneroEntity.Masculino)
public class GrupoUsuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
    public static interface InserirGrupoUsuario{}
    public static interface ExcluirGrupoUsuario{}

	@NotNull(message="O código do GRUPO é obrigatório.",groups = {InserirGrupoUsuario.class,ExcluirGrupoUsuario.class})
	@NotBlank(message="O código do GRUPO é obrigatório.",groups = {InserirGrupoUsuario.class,ExcluirGrupoUsuario.class})
    private Integer sequencialGrupo;

	@NotNull(message="O código do USUÁRIO é obrigatório.",groups = {InserirGrupoUsuario.class,ExcluirGrupoUsuario.class})
	@NotBlank(message="O código do USUÁRIO é obrigatório.",groups = {InserirGrupoUsuario.class,ExcluirGrupoUsuario.class})
	private Integer sequencialUsuario;

    public GrupoUsuario() {
    }
    
	public Integer getSequencialGrupo() {
		return sequencialGrupo;
	}

	public void setSequencialGrupo(Integer sequencialGrupo) {
		this.sequencialGrupo = sequencialGrupo;
	}

	public Integer getSequencialUsuario() {
		return sequencialUsuario;
	}

	public void setSequencialUsuario(Integer sequencialUsuario) {
		this.sequencialUsuario = sequencialUsuario;
	}

}