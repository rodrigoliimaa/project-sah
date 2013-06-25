package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Permissão", genero = GeneroEntity.Feminino)
public class Permissao implements Serializable{
	private static final long serialVersionUID = 1L;
	
    public static interface InserirPermissao{}
    public static interface AlterarPermissao{}
    public static interface ExcluirPermissao{}

   	@Null(message="O código da PERMISSÃO deve ser nulo.",groups = {InserirPermissao.class})
	@NotNull(message="O código da PERMISSÃO é obrigatório.",groups = {AlterarPermissao.class,ExcluirPermissao.class})
	private Integer sequencial;

    @NotNull(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirPermissao.class,AlterarPermissao.class})
    @NotBlank(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirPermissao.class,AlterarPermissao.class})
	private String descricao;

    @NotNull(message="O campo CHAVE é obrigatório.",groups = {InserirPermissao.class,AlterarPermissao.class})
    @NotBlank(message="O campo CHAVE é obrigatório.",groups = {InserirPermissao.class,AlterarPermissao.class})
    private String chave;
    
    public Permissao() {
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

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}    
}