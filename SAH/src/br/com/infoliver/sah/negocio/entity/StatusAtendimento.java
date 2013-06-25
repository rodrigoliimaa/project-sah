package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Status Atendimento", genero = GeneroEntity.Masculino)
public class StatusAtendimento implements Serializable {
	private static final long serialVersionUID = 1L;

    public static interface InserirStatusAtendimento{}
    public static interface AlterarStatusAtendimento{}
    public static interface ExcluirStatusAtendimento{}

    @Null(message="O campo STATUS ATENDIMENTO deve ser nulo.",groups = {InserirStatusAtendimento.class})
    @NotNull(message="O campo STATUS ATENDIMENTO é obrigatório.",groups = {AlterarStatusAtendimento.class,ExcluirStatusAtendimento.class})
	private Integer sequencial;
		
	@NotNull(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirStatusAtendimento.class,AlterarStatusAtendimento.class})
	@NotBlank(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirStatusAtendimento.class,AlterarStatusAtendimento.class})
	@Length(message = "O campo DESCRIÇÃO deve conter no máximo 50 dígitos.", max = 50, groups = {InserirStatusAtendimento.class,AlterarStatusAtendimento.class})
	private String descricao;

	public StatusAtendimento() {
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