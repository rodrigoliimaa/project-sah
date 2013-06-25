package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.List;

public class MovimentacaoCID implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String sigtap_co_cid;
	private String sigtap_no_cid;
	
	private List<MovimentacaoProcedimento> procedimentos;

	private Movimentacao movimentacao;
	
	public MovimentacaoCID() {

	}

	public String getSigtap_co_cid() {
		return sigtap_co_cid;
	}

	public void setSigtap_co_cid(String sigtap_co_cid) {
		this.sigtap_co_cid = sigtap_co_cid;
	}
	
	public String getSigtap_no_cid() {
		return sigtap_no_cid;
	}
	
	public void setSigtap_no_cid(String sigtap_no_cid) {
		this.sigtap_no_cid = sigtap_no_cid;
	}
	
	public List<MovimentacaoProcedimento> getProcedimentos() {
		return procedimentos;
	}
	
	public void setProcedimentos(List<MovimentacaoProcedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

}
