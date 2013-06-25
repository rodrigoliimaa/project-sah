package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Grupo", genero = GeneroEntity.Masculino)
public class Grupo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    public static interface InserirGrupo{}
    public static interface AlterarGrupo{}
    public static interface ExcluirGrupo{}

   	@Null(message="O código do GRUPO deve ser nulo.",groups = {InserirGrupo.class})
	@NotNull(message="O código do GRUPO é obrigatório.",groups = {AlterarGrupo.class,ExcluirGrupo.class})
	private Integer sequencial;

    @NotNull(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirGrupo.class,AlterarGrupo.class})
    @NotBlank(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirGrupo.class,AlterarGrupo.class})
	private String descricao;
    
    public Grupo() {
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