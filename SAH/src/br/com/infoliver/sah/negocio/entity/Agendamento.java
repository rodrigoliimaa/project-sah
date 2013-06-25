package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Feriado", genero = GeneroEntity.Masculino)
public class Agendamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Situação: agendado, atendido, cancelado, falta, presença confirmada, reagendamento
	public static final String SITUACAO_AGENDADO = "AGENDADO";
	public static final String SITUACAO_ATENDIDO = "ATENDIDO";
	public static final String SITUACAO_CANCELADO = "CANCELADO";
	public static final String SITUACAO_FALTA = "FALTA";
	public static final String SITUACAO_PRESENCA_CONFIRMADA = "PRESENCA_CONFIRMADA";
	public static final String SITUACAO_REAGENDAMENTO = "REAGENDAMENTO";
	
	// Turno: manhã, tarde
	public static final String TURNO_MANHA = "M";
	public static final String TURNO_TARDE = "T";
	
	public static final String MEDICO = "M";
	public static final String EQUIPE_MEDICA = "E";
	
	//-------------------------------
	public interface AlterarSituacaoAgendamento {}
	public interface InserirAgendamento {}
	//-------------------------------
	
//	@Null(message = "O código do AGENDAMENTO deve ser nulo", groups = {InserirAgendamento.class})
	@NotNull(message = "O código do AGENDAMENTO não deve ser nulo", groups = {AlterarSituacaoAgendamento.class})
	private Integer sequencial;
	
	@NotNull(message = "O campo MEDICO não deve ser nulo", groups = {InserirAgendamento.class})
	private Medico medico;
	
	@NotNull(message = "O campo PACIENTE não deve ser nulo", groups = {InserirAgendamento.class})
	private Paciente paciente;
	
	@NotNull(message = "O campo DATA não deve ser nulo", groups = {InserirAgendamento.class})
	private Date data;
	
	@NotNull(message = "O campo TURNO não deve ser nulo", groups = {InserirAgendamento.class})
	@NotBlank(message = "O campo TURNO não deve ser vazio", groups = {InserirAgendamento.class})
	private String turno;
	
	@NotNull(message = "O campo SITUAÇÃO não deve ser nulo", groups = {InserirAgendamento.class, AlterarSituacaoAgendamento.class})
	@NotBlank(message = "O campo SITUAÇÃO não deve ser vazio", groups = {InserirAgendamento.class, AlterarSituacaoAgendamento.class})
	private String situacao;
	
	private Agendamento reagendamento;
	
	// campo usado somente para pesquisa (para compor um intervalo de datas para a listagem de agendamentos)
	private Date dataFim;
	
	private EquipeMedica equipeMedica;
	
	private String tipo;
	
	private Date periodoInicial;
	
	private Date periodoFinal;
	
	public Agendamento() {

	}
	
	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
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
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public Agendamento getReagendamento() {
		return reagendamento;
	}
	
	public void setReagendamento(Agendamento reagendamento) {
		this.reagendamento = reagendamento;
	}
	
	public Date getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public EquipeMedica getEquipeMedica() {
		return equipeMedica;
	}
	
	public void setEquipeMedica(EquipeMedica equipeMedica) {
		this.equipeMedica = equipeMedica;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
