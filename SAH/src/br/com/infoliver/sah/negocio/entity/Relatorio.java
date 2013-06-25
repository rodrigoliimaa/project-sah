package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Relatório", genero = GeneroEntity.Masculino)
public class Relatorio implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer sequencial;
	private String nome;
	private String nomeArquivo;
	private String indicadorProcedimento;
	
	public Relatorio() {
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getIndicadorProcedimento() {
		return indicadorProcedimento;
	}

	public void setIndicadorProcedimento(String indicadorProcedimento) {
		this.indicadorProcedimento = indicadorProcedimento;
	}
}