package br.com.infoliver.sah.negocio.vo;

import java.io.Serializable;
import java.util.List;

public class ProcedimentoMedicoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<RelatorioProcedimentoMedicoVO> relatorioProcedimentoMedicoVO;
	private String caminhoRelatorio;
	
	public ProcedimentoMedicoVO(List<RelatorioProcedimentoMedicoVO> lista,String caminhoRelatorio) {
		this.relatorioProcedimentoMedicoVO=lista;
		this.caminhoRelatorio=caminhoRelatorio;
	}

	public List<RelatorioProcedimentoMedicoVO> getRelatorioProcedimentoMedicoVO() {
		return relatorioProcedimentoMedicoVO;
	}
	
	public void setRelatorioProcedimentoMedicoVO(
			List<RelatorioProcedimentoMedicoVO> relatorioProcedimentoMedicoVO) {
		this.relatorioProcedimentoMedicoVO = relatorioProcedimentoMedicoVO;
	}
	
	public String getCaminhoRelatorio() {
		return caminhoRelatorio;
	}
	
	public void setCaminhoRelatorio(String caminhoRelatorio) {
		this.caminhoRelatorio = caminhoRelatorio;
	}
	
	
}