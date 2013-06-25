package br.com.infoliver.sah.negocio.vo;

import java.io.Serializable;

import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.entity.ProcedimentoMedico;

public class RelatorioProcedimentoMedicoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Paciente paciente;
	private ProcedimentoMedico procedimentoMedico;
	private Medico medico;
	
	public RelatorioProcedimentoMedicoVO() {
	}

	public RelatorioProcedimentoMedicoVO(Paciente paciente,ProcedimentoMedico procedimentoMedico, Medico medico) {
		this.paciente=paciente;
		this.procedimentoMedico=procedimentoMedico;
		this.medico=medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public ProcedimentoMedico getProcedimentoMedico() {
		return procedimentoMedico;
	}
	
	public void setProcedimentoMedico(ProcedimentoMedico procedimentoMedico) {
		this.procedimentoMedico = procedimentoMedico;
	}
	
	public Medico getMedico() {
		return medico;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
}