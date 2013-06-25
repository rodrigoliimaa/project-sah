package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

public class MedicoEquipeMedica implements Serializable {

	private static final long serialVersionUID = 1L;

	private Medico medico;
	private EquipeMedica equipeMedica;
	
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public EquipeMedica getEquipeMedica() {
		return equipeMedica;
	}
	public void setEquipeMedica(EquipeMedica equipeMedica) {
		this.equipeMedica = equipeMedica;
	}
	
}
