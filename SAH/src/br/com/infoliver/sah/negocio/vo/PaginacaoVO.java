package br.com.infoliver.sah.negocio.vo;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component("paginacaoVO")
public class PaginacaoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Object entidade;
	private int quantidadePaginacao;
	private int inicioPaginacao;
	private int totalRegistros;

	public PaginacaoVO retornar(Object entidade,int quantidadePaginacao,int inicioPaginacao,int totalRegistros) {
		this.entidade = entidade;
		this.quantidadePaginacao = quantidadePaginacao;
		this.inicioPaginacao = inicioPaginacao;
		this.totalRegistros = totalRegistros;
		return this;
	}

	public Object getEntidade() {
		return entidade;
	}

	public void setEntidade(Object entidade) {
		this.entidade = entidade;
	}

	public int getQuantidadePaginacao() {
		return quantidadePaginacao;
	}

	public void setQuantidadePaginacao(int quantidadePaginacao) {
		this.quantidadePaginacao = quantidadePaginacao;
	}

	public int getInicioPaginacao() {
		return inicioPaginacao;
	}

	public void setInicioPaginacao(int inicioPaginacao) {
		this.inicioPaginacao = inicioPaginacao;
	}

	public int getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
}