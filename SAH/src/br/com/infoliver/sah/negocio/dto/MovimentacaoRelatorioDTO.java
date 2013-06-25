package br.com.infoliver.sah.negocio.dto;

import br.com.infoliver.sah.negocio.entity.Movimentacao;

public class MovimentacaoRelatorioDTO {
	
	private Integer sequencialPaciente;
	
	private String nomePaciente;

	private String codigoProcedimento;
	
	private String nomeProcedimento;

	private String categoriaTipo;
	
	private Integer quantidade;
	
	private Double numValorServAmb;
	
	private String numeroNota;
	
	private String nomeFantasia;
	
	public MovimentacaoRelatorioDTO(Movimentacao movimentacao) {
		this.sequencialPaciente = movimentacao.getPaciente().getSequencial();
		this.nomePaciente = movimentacao.getPaciente().getNome();
		this.codigoProcedimento = movimentacao.getProcedimentos().get(0).getSigtap_co_procedimento();
		this.nomeProcedimento = movimentacao.getProcedimentos().get(0).getSigtap_no_procedimento();
		this.categoriaTipo = movimentacao.getProcedimentos().get(0).getCategoriaTipo();
		this.quantidade = movimentacao.getProcedimentos().get(0).getQuantidade();
		this.numValorServAmb = movimentacao.getProcedimentos().get(0).getSigtap_vl_sa();
		this.numeroNota = movimentacao.getNumeroNota();
		this.nomeFantasia = movimentacao.getFornecedor().getNomeFantasia();
	}

	public Integer getSequencialPaciente() {
		return sequencialPaciente;
	}

	public void setSequencialPaciente(Integer sequencialPaciente) {
		this.sequencialPaciente = sequencialPaciente;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getCodigoProcedimento() {
		return codigoProcedimento;
	}

	public void setCodigoProcedimento(String codigoProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
	}

	public String getNomeProcedimento() {
		return nomeProcedimento;
	}

	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}

	public String getCategoriaTipo() {
		return categoriaTipo;
	}

	public void setCategoriaTipo(String categoriaTipo) {
		this.categoriaTipo = categoriaTipo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getNumValorServAmb() {
		return numValorServAmb;
	}

	public void setNumValorServAmb(Double numValorServAmb) {
		this.numValorServAmb = numValorServAmb;
	}

	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
}