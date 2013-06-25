package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.integracao.dao.IGrupoLaudoDAO;
import br.com.infoliver.sah.negocio.bo.IGrupoLaudoBO;
import br.com.infoliver.sah.negocio.entity.GrupoLaudo;
import br.com.infoliver.sah.negocio.entity.GrupoLaudoPaciente;
import br.com.infoliver.sah.negocio.entity.Paciente;

@Service("grupoLaudoBO")
public class GrupoLaudoBO implements IGrupoLaudoBO {
	@Autowired private IGrupoLaudoDAO grupoLaudoDAO;
	
	@Override
	public void inserir(GrupoLaudo grupoLaudo) throws BOException {
		grupoLaudoDAO.inserir(grupoLaudo);
	}

	@Override
	public void alterar(GrupoLaudo grupoLaudo) throws BOException {
		grupoLaudoDAO.alterar(grupoLaudo);
	}

	@Override
	public void excluir(GrupoLaudo grupoLaudo) throws BOException {
		grupoLaudoDAO.excluir(grupoLaudo);
	}

	@Override
	public List<GrupoLaudo> listar() {
		return grupoLaudoDAO.listar();
	}

	@Override
	public List<Paciente> listarPacienteGrupoLaudo(GrupoLaudo grupoLaudo) {
		return grupoLaudoDAO.listarPacienteGrupoLaudo(grupoLaudo);
	}

	@Override
	public void inserirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente)throws BOException {
		grupoLaudoDAO.inserirGrupoLaudoPaciente(grupoLaudoPaciente);
	}

	@Override
	public void excluirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente)throws BOException {
		grupoLaudoDAO.excluirGrupoLaudoPaciente(grupoLaudoPaciente);
	}
}