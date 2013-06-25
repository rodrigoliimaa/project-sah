package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "PessoaTipoDocumento", genero = GeneroEntity.Masculino)
public class PessoaTipoDocumento implements Serializable {

	private static final long serialVersionUID = 1L;

    public static interface InserirPessoaTipoDocumento{}
    public static interface AlterarPessoaTipoDocumento{}
    public static interface ExcluirPessoaTipoDocumento{}
	
    @Null(message="O campo PESSOA TIPO DOCUMENTO deve ser nulo.",groups = {InserirPessoaTipoDocumento.class})
    @NotNull(message="O campo PESSOA TIPO DOCUMENTO é obrigatório.",groups = {AlterarPessoaTipoDocumento.class,ExcluirPessoaTipoDocumento.class})
	private Integer sequencial;
	
    @NotNull(message="O campo NOME é obrigatório.",
    		groups = {InserirPessoaTipoDocumento.class, AlterarPessoaTipoDocumento.class})
    	@NotBlank(message="O campo NOME é obrigatório.",
    		groups = {InserirPessoaTipoDocumento.class,AlterarPessoaTipoDocumento.class})
    private String descricao;
    
	private Date dataHoraCadastro;

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
	public Date getDataHoraCadastro() {
		return dataHoraCadastro;
	}
	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
	
}
