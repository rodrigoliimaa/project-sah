package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Grupo Laudo Paciente", genero = GeneroEntity.Masculino)
public class GrupoLaudoPaciente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static interface InserirGrupoLaudoPaciente{}
	public static interface ExcluirGrupoLaudoPaciente{}

	@NotNull(message = "O código do GRUPO LAUDO é obrigatório.", groups = {InserirGrupoLaudoPaciente.class, ExcluirGrupoLaudoPaciente.class })
	@NotBlank(message = "O código do GRUPO LAUDO é obrigatório.", groups = {InserirGrupoLaudoPaciente.class, ExcluirGrupoLaudoPaciente.class })
	private Integer sequencialGrupo;

	@NotNull(message = "O código do PACIENTE é obrigatório.", groups = {InserirGrupoLaudoPaciente.class, ExcluirGrupoLaudoPaciente.class })
	@NotBlank(message = "O código do PACIENTE é obrigatório.", groups = {InserirGrupoLaudoPaciente.class, ExcluirGrupoLaudoPaciente.class })
	private Integer sequencialPaciente;

	public GrupoLaudoPaciente() {
	}

	public Integer getSequencialGrupo() {
		return sequencialGrupo;
	}

	public void setSequencialGrupo(Integer sequencialGrupo) {
		this.sequencialGrupo = sequencialGrupo;
	}

	public Integer getSequencialPaciente() {
		return sequencialPaciente;
	}

	public void setSequencialPaciente(Integer sequencialPaciente) {
		this.sequencialPaciente = sequencialPaciente;
	}
}