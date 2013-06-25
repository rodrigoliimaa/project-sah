package br.com.infoliver.sah.negocio.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.integracao.dao.IGrupoUsuarioDAO;
import br.com.infoliver.sah.negocio.bo.IGrupoUsuarioBO;
import br.com.infoliver.sah.negocio.entity.GrupoUsuario;

@Service("grupoUsuarioBO")
public class GrupoUsuarioBO implements IGrupoUsuarioBO {

	@Autowired
	private IGrupoUsuarioDAO grupoUsuarioDAO;
	
	@Override
	public void inserir(GrupoUsuario grupoUsuario) throws BOException {
		grupoUsuarioDAO.inserir(grupoUsuario);
	}

	@Override
	public void excluir(GrupoUsuario grupoUsuario) throws BOException {
		grupoUsuarioDAO.excluir(grupoUsuario);
	}
}