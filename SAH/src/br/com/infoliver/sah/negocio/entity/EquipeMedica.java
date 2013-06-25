package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.List;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "EquipeMedica", genero = GeneroEntity.Masculino)
public class EquipeMedica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer sequencial;
	private String descricao;
	private Usuario usuarioResponsavel;
	private List<Medico> medicos;
	private List<Horario> horarios;
	
	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}
	
	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}
	
	public Integer getSequencial() {
		return sequencial;
	}
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Medico> getMedicos() {
		return medicos;
	}
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
	public List<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	
}
