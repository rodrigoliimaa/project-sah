package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Movimentação", genero = GeneroEntity.Feminino)
public class Auditivo implements Serializable {

	// -------------------------------
	public interface AlterarAuditivo {
	}

	public interface InserirAuditivo {
	}

	// -------------------------------

	private static final long serialVersionUID = 1L;

	@NotNull(message = "O código da MOVIMENTAÇÃO não deve ser nulo", groups = { AlterarAuditivo.class })
	private Integer sequencial;

	private String apacBpi;

	private Date dataAutorizacao;

	private Date dataEncaminhamento;

	private Date dataEntrada;

	private Date dataEntrega;

	@NotNull(message = "A data de solicitação da MOVIMENTAÇÃO não deve ser nula", groups = {
			AlterarAuditivo.class, InserirAuditivo.class })
	private Date dataSolicitacao;

	private Date dataVencimento;

	private String numeroNota;

	private String observacao;

	private String sigtap_dt_competencia;


	@NotNull(message = "A cid principal da MOVIMENTAÇÃO não deve ser nula", groups = { InserirAuditivo.class })
	private MovimentacaoCID cidPrincipal;

	private MovimentacaoCID cidSecundario;
	private MovimentacaoCID cidCausasAssociadas;

	private Fornecedor fornecedor;

	@NotNull(message = "O médico da MOVIMENTAÇÃO não deve ser nulo", groups = { InserirAuditivo.class })
	private Medico medico;

	@NotNull(message = "O paciente da MOVIMENTAÇÃO não deve ser nulo", groups = { InserirAuditivo.class })
	private Paciente paciente;

	private List<MovimentacaoProcedimento> procedimentos;

	@NotNull(message = "O programa da MOVIMENTAÇÃO não deve ser nulo", groups = { InserirAuditivo.class })
	private Programa programa;

	// somente para pesquisa, não persistir
	private Date dataInicioSolicitacao;
	private Date dataInicioSituacao;
	private String situacao;
	private Date dataFimSolicitacao;
	private Date dataFimSituacao;

	public Auditivo() {

	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getApacBpi() {
		return apacBpi;
	}

	public void setApacBpi(String apacBpi) {
		this.apacBpi = apacBpi;
	}

	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public Date getDataEncaminhamento() {
		return dataEncaminhamento;
	}

	public void setDataEncaminhamento(Date dataEncaminhamento) {
		this.dataEncaminhamento = dataEncaminhamento;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getSigtap_dt_competencia() {
		return sigtap_dt_competencia;
	}

	public void setSigtap_dt_competencia(String sigtap_dt_competencia) {
		this.sigtap_dt_competencia = sigtap_dt_competencia;
	}

	public MovimentacaoCID getCidPrincipal() {
		return cidPrincipal;
	}

	public void setCidPrincipal(MovimentacaoCID cidPrincipal) {
		this.cidPrincipal = cidPrincipal;
	}

	public MovimentacaoCID getCidSecundario() {
		return cidSecundario;
	}

	public void setCidSecundario(MovimentacaoCID cidSecundario) {
		this.cidSecundario = cidSecundario;
	}

	public MovimentacaoCID getCidCausasAssociadas() {
		return cidCausasAssociadas;
	}

	public void setCidCausasAssociadas(MovimentacaoCID cidCausasAssociadas) {
		this.cidCausasAssociadas = cidCausasAssociadas;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<MovimentacaoProcedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<MovimentacaoProcedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Date getDataInicioSolicitacao() {
		return dataInicioSolicitacao;
	}

	public void setDataInicioSolicitacao(Date dataInicioSolicitacao) {
		this.dataInicioSolicitacao = dataInicioSolicitacao;
	}

	public Date getDataInicioSituacao() {
		return dataInicioSituacao;
	}

	public void setDataInicioSituacao(Date dataInicioSituacao) {
		this.dataInicioSituacao = dataInicioSituacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getDataFimSolicitacao() {
		return dataFimSolicitacao;
	}

	public void setDataFimSolicitacao(Date dataFimSolicitacao) {
		this.dataFimSolicitacao = dataFimSolicitacao;
	}

	public Date getDataFimSituacao() {
		return dataFimSituacao;
	}

	public void setDataFimSituacao(Date dataFimSituacao) {
		this.dataFimSituacao = dataFimSituacao;
	}

	public Double getValorTotal() {
		Double valor = 0.0;
		valor = valor + getValorTotal(cidCausasAssociadas);
		valor = valor + getValorTotal(cidPrincipal);
		valor = valor + getValorTotal(cidSecundario);
		return valor;
	}
	
	private Double getValorTotal(MovimentacaoCID cid) {
		Double valor = 0.0;
		if (cid != null && cid.getProcedimentos() != null) {
			for (MovimentacaoProcedimento procedimento : cid.getProcedimentos()) {
				valor = valor + procedimento.getValorTotal();
			}
		}
		return valor;
	}
}
