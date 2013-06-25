package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Horário", genero = GeneroEntity.Masculino)
public class Horario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//-------------------------------
	public interface InserirHorario {}
	//-------------------------------
	
	@NotNull(message = "O campo MÉDICO é obrigatório", groups = {InserirHorario.class})
	private Medico medico;
	
	@NotNull(message = "O campo DIA DA SEMANA é obrigatório", groups = {InserirHorario.class})
	private Integer diaSemana;
	
	@NotNull(message = "O campo TURNO é obrigatório", groups = {InserirHorario.class})
	private String turno;
	
	@NotNull(message = "O campo NÚMERO MÁXIMO DE AGENDAMENTOS é obrigatório", groups = {InserirHorario.class})
	private Integer numeroMaximoAgendamentos;
	
	public Horario() {

	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Integer getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(Integer diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Integer getNumeroMaximoAgendamentos() {
		return numeroMaximoAgendamentos;
	}

	public void setNumeroMaximoAgendamentos(Integer numeroMaximoAgendamentos) {
		this.numeroMaximoAgendamentos = numeroMaximoAgendamentos;
	}

}
