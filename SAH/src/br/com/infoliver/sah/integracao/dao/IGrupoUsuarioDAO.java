package br.com.infoliver.sah.integracao.dao;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.GrupoUsuario;

public interface IGrupoUsuarioDAO {
	Integer inserir(GrupoUsuario grupoUsuario)throws DAOException;

	Integer excluir(GrupoUsuario grupoUsuario)throws DAOException;
}