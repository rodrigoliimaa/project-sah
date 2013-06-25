package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

public class Programa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int PROGRAMA_SEQUENCIAL_AUDITIVO = 2;
	public static final int PROGRAMA_SEQUENCIAL_ORTESE_PROTESE = 1;
	
	public static final String PROGRAMA_DESCRICAO_AUDITIVO = "AUDITIVO";
	public static final String PROGRAMA_DESCRICAO_ORTESE_PROTESE = "ORTESE E PROTESE";
	
	private Integer sequencial;
	
	private String descricao;
	
	public Programa() {

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

	public boolean isAuditivo() {
		if (sequencial == PROGRAMA_SEQUENCIAL_AUDITIVO) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isOrteseProtese() {
		if (sequencial == PROGRAMA_SEQUENCIAL_ORTESE_PROTESE) {
			return true;
		} else {
			return false;
		}
	}

}
