package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Log de Acesso", genero = GeneroEntity.Masculino)
public class LogAcesso implements Serializable{
	private static final long serialVersionUID = 1L;
	
    public static interface InserirLogAcesso{}

   	@Null(message="O código do GRUPO deve ser nulo.",groups = {InserirLogAcesso.class})
	private Integer sequencial;

    @NotNull(message="O campo NOME DO USUÁRIO é obrigatório.",groups = {InserirLogAcesso.class})
    @NotBlank(message="O campo NOME DO USUÁRIO é obrigatório.",groups = {InserirLogAcesso.class})
	private Integer sequencialUsuario;

    @NotNull(message="O campo NOME DO USUÁRIO é obrigatório.",groups = {InserirLogAcesso.class})
    @NotBlank(message="O campo NOME DO USUÁRIO é obrigatório.",groups = {InserirLogAcesso.class})
    private String nomeUsuario;
    
    private Date dataInicial;
    
    private Date dataFinal;

    private Date dataHoraCadastro;
    
    public LogAcesso() {
    }

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public Integer getSequencialUsuario() {
		return sequencialUsuario;
	}

	public void setSequencialUsuario(Integer sequencialUsuario) {
		this.sequencialUsuario = sequencialUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
}