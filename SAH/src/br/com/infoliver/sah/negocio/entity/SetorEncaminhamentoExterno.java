package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade="Setor Encaminhamento Externo", genero=GeneroEntity.Masculino)
public class SetorEncaminhamentoExterno implements Serializable {
	private static final long serialVersionUID = 1L;

    public static interface InserirSetor{}
    public static interface AlterarSetor{}
    public static interface ExcluirSetor{}

    @Null(message="O campo SETOR deve ser nulo.",groups = {InserirSetor.class})
    @NotNull(message="O campo SETOR é obrigatório.",
    	groups = {AlterarSetor.class,ExcluirSetor.class,InserirSetor.class})
	private Integer sequencial;
		
	@NotNull(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirSetor.class,AlterarSetor.class})
	@NotBlank(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirSetor.class,AlterarSetor.class})
	@Length(message = "O campo DESCRIÇÃO deve conter no máximo 100 dígitos.", max = 100, groups = {InserirSetor.class, AlterarSetor.class})
	private String descricao;
	private Date dataCadastro;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public SetorEncaminhamentoExterno() {
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
