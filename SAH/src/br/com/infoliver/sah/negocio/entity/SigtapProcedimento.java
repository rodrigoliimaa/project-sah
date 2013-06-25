package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.List;

public class SigtapProcedimento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String co_procedimento;
	private String no_procedimento;
	private String tp_complexidade;
	private String tp_sexo;
	private int qt_maxima_execucao;
	private int qt_dias_permanencia;
	private int qt_pontos;
	private int vl_idade_minima;
	private int vl_idade_maxima;
	private Double vl_sh;
	private Double vl_sa;
	private Double vl_sp;
	private String co_financiamento;
	private String co_rubrica;
	private String dt_competencia;
	private String dt_procedimento;
	
	private List<SigtapCID> sigtapCIDs;
	private List<SigtapOcupacao> sigtapOcupacoes;
	
	public SigtapProcedimento() {

	}

	public String getCo_procedimento() {
		return co_procedimento;
	}

	public void setCo_procedimento(String co_procedimento) {
		this.co_procedimento = co_procedimento;
	}

	public String getNo_procedimento() {
		return no_procedimento;
	}

	public void setNo_procedimento(String no_procedimento) {
		this.no_procedimento = no_procedimento;
	}

	public String getTp_complexidade() {
		return tp_complexidade;
	}

	public void setTp_complexidade(String tp_complexidade) {
		this.tp_complexidade = tp_complexidade;
	}

	public String getTp_sexo() {
		return tp_sexo;
	}

	public void setTp_sexo(String tp_sexo) {
		this.tp_sexo = tp_sexo;
	}

	public int getQt_maxima_execucao() {
		return qt_maxima_execucao;
	}

	public void setQt_maxima_execucao(int qt_maxima_execucao) {
		this.qt_maxima_execucao = qt_maxima_execucao;
	}

	public int getQt_dias_permanencia() {
		return qt_dias_permanencia;
	}

	public void setQt_dias_permanencia(int qt_dias_permanencia) {
		this.qt_dias_permanencia = qt_dias_permanencia;
	}

	public int getQt_pontos() {
		return qt_pontos;
	}

	public void setQt_pontos(int qt_pontos) {
		this.qt_pontos = qt_pontos;
	}

	public int getVl_idade_minima() {
		return vl_idade_minima;
	}

	public void setVl_idade_minima(int vl_idade_minima) {
		this.vl_idade_minima = vl_idade_minima;
	}

	public int getVl_idade_maxima() {
		return vl_idade_maxima;
	}

	public void setVl_idade_maxima(int vl_idade_maxima) {
		this.vl_idade_maxima = vl_idade_maxima;
	}

	public Double getVl_sh() {
		return vl_sh;
	}

	public void setVl_sh(Double vl_sh) {
		this.vl_sh = vl_sh;
	}

	public Double getVl_sa() {
		return vl_sa;
	}

	public void setVl_sa(Double vl_sa) {
		this.vl_sa = vl_sa;
	}

	public Double getVl_sp() {
		return vl_sp;
	}

	public void setVl_sp(Double vl_sp) {
		this.vl_sp = vl_sp;
	}

	public String getCo_financiamento() {
		return co_financiamento;
	}

	public void setCo_financiamento(String co_financiamento) {
		this.co_financiamento = co_financiamento;
	}

	public String getCo_rubrica() {
		return co_rubrica;
	}

	public void setCo_rubrica(String co_rubrica) {
		this.co_rubrica = co_rubrica;
	}

	public String getDt_competencia() {
		return dt_competencia;
	}
	
	public void setDt_competencia(String dt_competencia) {
		this.dt_competencia = dt_competencia;
	}
	
	public String getDt_procedimento() {
		return dt_procedimento;
	}

	public void setDt_procedimento(String dt_procedimento) {
		this.dt_procedimento = dt_procedimento;
	}

	public List<SigtapCID> getSigtapCIDs() {
		return sigtapCIDs;
	}
	
	public void setSigtapCIDs(List<SigtapCID> sigtapCIDs) {
		this.sigtapCIDs = sigtapCIDs;
	}
	
	public List<SigtapOcupacao> getSigtapOcupacoes() {
		return sigtapOcupacoes;
	}
	
	public void setSigtapOcupacoes(List<SigtapOcupacao> sigtapOcupacoes) {
		this.sigtapOcupacoes = sigtapOcupacoes;
	}

}
