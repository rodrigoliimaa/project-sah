package br.com.infoliver.sah.integracao.dao;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.GrupoPermissao;

public interface IGrupoPermissaoDAO {
	Integer inserir(GrupoPermissao grupoPermissao)throws DAOException;

	Integer excluir(GrupoPermissao grupoPermissao)throws DAOException;
}