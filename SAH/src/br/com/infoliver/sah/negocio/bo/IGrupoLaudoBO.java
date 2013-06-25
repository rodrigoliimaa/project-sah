package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.negocio.entity.GrupoLaudo;
import br.com.infoliver.sah.negocio.entity.GrupoLaudoPaciente;
import br.com.infoliver.sah.negocio.entity.Paciente;

public interface IGrupoLaudoBO {
	List<GrupoLaudo> listar();
	
	List<Paciente> listarPacienteGrupoLaudo(GrupoLaudo grupoLaudo);
	
	void inserir(GrupoLaudo grupoLaudo)throws BOException;

	void alterar(GrupoLaudo grupoLaudo)throws BOException;

	void excluir(GrupoLaudo grupoLaudo)throws BOException;
	
	void inserirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente)throws BOException;
	
	void excluirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente)throws BOException;
}