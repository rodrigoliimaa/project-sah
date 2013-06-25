package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.Grupo;

public interface IGrupoDAO {
	Integer inserir(Grupo grupo)throws DAOException;

	Integer alterar(Grupo grupo)throws DAOException;

	Integer excluir(Grupo grupo)throws DAOException;

	List<Grupo> listar();
}