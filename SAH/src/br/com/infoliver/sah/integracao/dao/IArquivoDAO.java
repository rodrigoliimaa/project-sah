package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.Arquivo;

public interface IArquivoDAO {
	
	void inserir(Arquivo arquivo)throws DAOException;
	
	List<Arquivo> excluir(Arquivo arquivo)throws DAOException;
	
	List<Arquivo> listar(Integer sequencialPaciente);
	
	Arquivo consultarImagem(Integer sequencial);
}