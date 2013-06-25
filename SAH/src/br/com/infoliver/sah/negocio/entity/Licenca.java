package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Licença", genero = GeneroEntity.Feminino)
public class Licenca implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//-------------------------------
	public interface AlterarLicenca {}
	public interface ExcluirLicenca {}
	public interface InserirLicenca {}
	//-------------------------------
	
	@NotNull(message = "O código da LICENCA nao deve ser nulo", groups = {AlterarLicenca.class, ExcluirLicenca.class})
	private Integer sequencial;
	
	@NotNull(message = "O campo MOTIVO é obrigatório", groups = {AlterarLicenca.class, InserirLicenca.class})
	private Motivo motivo;
	
	@NotNull(message = "O campo DATA DE INÍCIO é obrigatório", groups = {AlterarLicenca.class, InserirLicenca.class})
	private Date dataInicio;
	
	private Date dataFim;
	
	private String observacao;
	
	@NotNull(message = "O campo MÉDICO é obrigatório", groups = {AlterarLicenca.class, InserirLicenca.class})
	private Medico medico;
	
	public Licenca() {

	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
}
