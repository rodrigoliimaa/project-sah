package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.exception.DBException;
import br.com.infoliver.sah.integracao.dao.IGrupoDAO;
import br.com.infoliver.sah.negocio.bo.IGrupoBO;
import br.com.infoliver.sah.negocio.entity.Grupo;

@Service("grupoBO")
public class GrupoBO implements IGrupoBO {

	@Autowired
	private IGrupoDAO grupoDAO;
	
	@Override
	public void inserir(Grupo grupo) throws BOException {
		try {
			grupoDAO.inserir(grupo);
		} catch (Exception e) {
			throw new DBException(e,grupo.getDescricao());
		}
	}

	@Override
	public void alterar(Grupo grupo) throws BOException {
		try {
			grupoDAO.alterar(grupo);
		} catch (Exception e) {
			throw new DBException(e,grupo.getDescricao());
		}
	}

	@Override
	public void excluir(Grupo grupo) throws BOException {
		grupoDAO.excluir(grupo);
	}

	@Override
	public List<Grupo> listar() {
		return grupoDAO.listar();
	}
}