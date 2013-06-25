package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Grupo Laudo", genero = GeneroEntity.Masculino)
public class GrupoLaudo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    public static interface InserirGrupoLaudo{}
    public static interface AlterarGrupoLaudo{}
    public static interface ExcluirGrupoLaudo{}

   	@Null(message="O código do GRUPO LAUDO deve ser nulo.",groups = {InserirGrupoLaudo.class})
	@NotNull(message="O código do GRUPO LAUDO é obrigatório.",groups = {AlterarGrupoLaudo.class,ExcluirGrupoLaudo.class})
	private Integer sequencial;

    @NotNull(message="O campo DESCRIÇÃO LAUDO é obrigatório.",groups = {InserirGrupoLaudo.class,AlterarGrupoLaudo.class})
    @NotBlank(message="O campo DESCRIÇÃO LAUDO é obrigatório.",groups = {InserirGrupoLaudo.class,AlterarGrupoLaudo.class})
	private String descricao;
    
    public GrupoLaudo() {
    }

	public GrupoLaudo(Integer sequencial) {
		this.sequencial=sequencial;
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