package br.com.infoliver.sah.apresentacao.servlet.util;

import java.util.List;

import flex.messaging.io.ArrayList;

import br.com.infoliver.sah.negocio.entity.Paciente;

public class KeyValue {

	private String apacBpi;
	
	private String observacao;
	
	@SuppressWarnings("unchecked")
	private List<Paciente> pacientes = new ArrayList();
	
	@SuppressWarnings("unused")
	private int quantidadePacientes;
	
	public KeyValue() {}
	
	public void adicionarPaciente(Paciente paciente) {
		pacientes.add(paciente);
	}

	public String getApacBpi() {
		return apacBpi;
	}

	public void setApacBpi(String key) {
		this.apacBpi = key;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String descricao) {
		this.observacao = descricao;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	public int getQuantidadePacientes() {
		return pacientes.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apacBpi == null) ? 0 : apacBpi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyValue other = (KeyValue) obj;
		if (apacBpi == null) {
			if (other.apacBpi != null)
				return false;
		} else if (!apacBpi.equals(other.apacBpi))
			return false;
		return true;
	}
	
}
