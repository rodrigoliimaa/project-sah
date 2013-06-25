package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

public class SigtapOcupacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String co_ocupacao;
	
	public SigtapOcupacao() {
		
	}

	public String getCo_ocupacao() {
		return co_ocupacao;
	}

	public void setCo_ocupacao(String co_ocupacao) {
		this.co_ocupacao = co_ocupacao;
	}

}
