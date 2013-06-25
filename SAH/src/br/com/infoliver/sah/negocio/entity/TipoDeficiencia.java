package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Tipo Deficiencia", genero = GeneroEntity.Feminino)
public class TipoDeficiencia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static interface InserirTipoDeficiencia{}
	public static interface AlterarTipoDeficiencia{}
	public static interface ExcluirTipoDeficiencia{}
	
	@Null(message="O campo TIPO DEFICIÊNCIA deve ser nulo.",groups = {InserirTipoDeficiencia.class})
    @NotNull(message="O campo TIPO DEFICIÊNCIA é obrigatório.",groups = {AlterarTipoDeficiencia.class,ExcluirTipoDeficiencia.class})
	private Integer sequencial;
	
	@NotNull(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirTipoDeficiencia.class,AlterarTipoDeficiencia.class})
	@NotBlank(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirTipoDeficiencia.class,AlterarTipoDeficiencia.class})
	@Length(message = "O campo DESCRIÇÃO deve conter no máximo 50 dígitos.", max = 50, groups = {InserirTipoDeficiencia.class, AlterarTipoDeficiencia.class})
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
