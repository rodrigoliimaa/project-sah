package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.GrupoLaudo;
import br.com.infoliver.sah.negocio.entity.GrupoLaudoPaciente;
import br.com.infoliver.sah.negocio.entity.Paciente;

public interface IGrupoLaudoDAO {

	List<GrupoLaudo> listar();

	List<Paciente> listarPacienteGrupoLaudo(GrupoLaudo grupoLaudo);
	
	Integer inserir(GrupoLaudo grupoLaudo)throws DAOException;

	Integer alterar(GrupoLaudo grupoLaudo)throws DAOException;

	Integer excluir(GrupoLaudo grupoLaudo)throws DAOException;
	
	Integer inserirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente) throws DAOException;
	
	Integer excluirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente) throws DAOException;
}