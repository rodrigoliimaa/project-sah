package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

public class MovimentacaoProcedimento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer sequencial;

	private int quantidade;
	private String categoriaTipo;

	private String sigtap_co_procedimento;
	private String sigtap_no_procedimento; // não persistir
	private Double sigtap_vl_sa;
	private Double sigtap_vl_sh;
	private Double sigtap_vl_sp;
	private int sigtap_qt_maxima_execucao;
	private int sigtap_vl_idade_minima;
	private int sigtap_vl_idade_maxima;
	private String sigtap_tp_sexo;
	private String sigtap_dt_competencia;

	private MovimentacaoCID cid;
	private Equipamento equipamento;
	private Movimentacao movimentacao;
	
	public MovimentacaoProcedimento() {

	}
	
	public Integer getSequencial() {
		return sequencial;
	}
	
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCategoriaTipo() {
		return categoriaTipo;
	}

	public void setCategoriaTipo(String categoriaTipo) {
		this.categoriaTipo = categoriaTipo;
	}

	public String getSigtap_co_procedimento() {
		return sigtap_co_procedimento;
	}

	public void setSigtap_co_procedimento(String sigtap_co_procedimento) {
		this.sigtap_co_procedimento = sigtap_co_procedimento;
	}
	
	public String getSigtap_no_procedimento() {
		return sigtap_no_procedimento;
	}
	
	public void setSigtap_no_procedimento(String sigtap_no_procedimento) {
		this.sigtap_no_procedimento = sigtap_no_procedimento;
	}

	public Double getSigtap_vl_sa() {
		return sigtap_vl_sa;
	}

	public void setSigtap_vl_sa(Double sigtap_vl_sa) {
		this.sigtap_vl_sa = sigtap_vl_sa;
	}

	public Double getSigtap_vl_sh() {
		return sigtap_vl_sh;
	}

	public void setSigtap_vl_sh(Double sigtap_vl_sh) {
		this.sigtap_vl_sh = sigtap_vl_sh;
	}

	public Double getSigtap_vl_sp() {
		return sigtap_vl_sp;
	}

	public void setSigtap_vl_sp(Double sigtap_vl_sp) {
		this.sigtap_vl_sp = sigtap_vl_sp;
	}

	public int getSigtap_qt_maxima_execucao() {
		return sigtap_qt_maxima_execucao;
	}

	public void setSigtap_qt_maxima_execucao(int sigtap_qt_maxima_execucao) {
		this.sigtap_qt_maxima_execucao = sigtap_qt_maxima_execucao;
	}

	public int getSigtap_vl_idade_minima() {
		return sigtap_vl_idade_minima;
	}

	public void setSigtap_vl_idade_minima(int sigtap_vl_idade_minima) {
		this.sigtap_vl_idade_minima = sigtap_vl_idade_minima;
	}

	public int getSigtap_vl_idade_maxima() {
		return sigtap_vl_idade_maxima;
	}

	public void setSigtap_vl_idade_maxima(int sigtap_vl_idade_maxima) {
		this.sigtap_vl_idade_maxima = sigtap_vl_idade_maxima;
	}

	public String getSigtap_tp_sexo() {
		return sigtap_tp_sexo;
	}

	public void setSigtap_tp_sexo(String sigtap_tp_sexo) {
		this.sigtap_tp_sexo = sigtap_tp_sexo;
	}
	
	public String getSigtap_dt_competencia() {
		return sigtap_dt_competencia;
	}

	public void setSigtap_dt_competencia(String sigtap_dt_competencia) {
		this.sigtap_dt_competencia = sigtap_dt_competencia;
	}

	public MovimentacaoCID getCid() {
		return cid;
	}
	
	public void setCid(MovimentacaoCID cid) {
		this.cid = cid;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Double getValorTotal() {
		return (this.sigtap_vl_sa + this.sigtap_vl_sh + this.sigtap_vl_sp) * this.quantidade;
	}

}
