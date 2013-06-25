package br.com.infoliver.sah.integracao.dao;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.Empresa;

public interface IEmpresaDAO {
	
	Empresa consultar();
	
	void inserir(Empresa empresa)throws DAOException;
	
	void alterar(Empresa empresa)throws DAOException;
}