package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Tipo Status Atendimento", genero = GeneroEntity.Masculino)
public class TipoStatusAtendimento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static interface InserirTipoStatusAtendimento{}
    public static interface AlterarTipoStatusAtendimento{}
    public static interface ExcluirTipoStatusAtendimento{}
	
    @Null(message="O campo TIPO STATUS ATENDIMENTO deve ser nulo.",groups = {InserirTipoStatusAtendimento.class})
    @NotNull(message="O campo TIPO STATUS ATENDIMENTO é obrigatório.",groups = {AlterarTipoStatusAtendimento.class,ExcluirTipoStatusAtendimento.class})
	private Integer sequencial;
	
    @NotNull(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirTipoStatusAtendimento.class,AlterarTipoStatusAtendimento.class})
	@NotBlank(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirTipoStatusAtendimento.class,AlterarTipoStatusAtendimento.class})
	@Length(message = "O campo DESCRIÇÃO deve conter no máximo 50 dígitos.", max = 50, groups = {InserirTipoStatusAtendimento.class, AlterarTipoStatusAtendimento.class})
    private String descricao;
	
    private Date dataHoraCadastro;

    public TipoStatusAtendimento() {}
    
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
