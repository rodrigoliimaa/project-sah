package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Procedimento Médico", genero = GeneroEntity.Masculino)
public class ProcedimentoMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer sequencial;
	private String tipoProcedimento;
	private Integer quantidadeLinha;
	private String codigoPrincipal;
	private String descricaoPrincipal;
	private String codigoSecundario;
	private String descricaoSecundario;
	private Integer sequencialProcedimentoPai;

	public ProcedimentoMedico() {
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getTipoProcedimento() {
		return tipoProcedimento;
	}

	public void setTipoProcedimento(String tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento;
	}

	public Integer getQuantidadeLinha() {
		return quantidadeLinha;
	}

	public void setQuantidadeLinha(Integer quantidadeLinha) {
		this.quantidadeLinha = quantidadeLinha;
	}

	public String getCodigoPrincipal() {
		return codigoPrincipal;
	}

	public void setCodigoPrincipal(String codigoPrincipal) {
		this.codigoPrincipal = codigoPrincipal;
	}

	public String getDescricaoPrincipal() {
		return descricaoPrincipal;
	}

	public void setDescricaoPrincipal(String descricaoPrincipal) {
		this.descricaoPrincipal = descricaoPrincipal;
	}

	public String getCodigoSecundario() {
		return codigoSecundario;
	}

	public void setCodigoSecundario(String codigoSecundario) {
		this.codigoSecundario = codigoSecundario;
	}

	public String getDescricaoSecundario() {
		return descricaoSecundario;
	}

	public void setDescricaoSecundario(String descricaoSecundario) {
		this.descricaoSecundario = descricaoSecundario;
	}

	public Integer getSequencialProcedimentoPai() {
		return sequencialProcedimentoPai;
	}

	public void setSequencialProcedimentoPai(Integer sequencialProcedimentoPai) {
		this.sequencialProcedimentoPai = sequencialProcedimentoPai;
	}
}