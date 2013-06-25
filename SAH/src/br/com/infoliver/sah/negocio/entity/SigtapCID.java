package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.List;

public class SigtapCID implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String co_cid;
	private String no_cid;

	private List<SigtapProcedimento> sigtapProcedimentos;
	
	public SigtapCID() {

	}

	public String getCo_cid() {
		return co_cid;
	}

	public void setCo_cid(String co_cid) {
		this.co_cid = co_cid;
	}

	public String getNo_cid() {
		return no_cid;
	}

	public void setNo_cid(String no_cid) {
		this.no_cid = no_cid;
	}
	
	public List<SigtapProcedimento> getSigtapProcedimentos() {
		return sigtapProcedimentos;
	}

	public void setSigtapProcedimentos(List<SigtapProcedimento> sigtapProcedimentos) {
		this.sigtapProcedimentos = sigtapProcedimentos;
	}

}
