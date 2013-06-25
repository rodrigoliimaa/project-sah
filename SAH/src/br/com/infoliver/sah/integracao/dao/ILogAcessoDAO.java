package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.LogAcesso;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface ILogAcessoDAO {
	
	void inserir(LogAcesso logAcesso)throws DAOException;

	Integer totalRegitrosParaPaginacao(PaginacaoVO logAcesso);

	List<LogAcesso> listarPaginado(PaginacaoVO logAcesso);
}