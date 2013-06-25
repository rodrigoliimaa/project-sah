package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.PreAtendimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface IPreAtendimentoDAO {

	void inserir(PreAtendimento preAtendimento) throws DAOException;
	void alterar(PreAtendimento preAtendimento) throws DAOException;
	void excluir(PreAtendimento preAtendimento) throws DAOException;
	List<PreAtendimento> listar(PaginacaoVO preAtendimento);
	List<PreAtendimento> listarPreAtendimentoPaginado(PaginacaoVO preAtendimento);
	List<PreAtendimento> listarPreAtendimentoPaginadoFiltro(PaginacaoVO preAtendimento);
	List<PreAtendimento> listarPreAtendimentoAtendidos(PaginacaoVO preAtendimento);
	List<PreAtendimento> listarEncaminhamentoExterno(PreAtendimento preAtendimento);
	Integer totalRegitrosParaPaginacao(PaginacaoVO preAtendimento);
	Integer totalRegitrosParaPaginar();
}
