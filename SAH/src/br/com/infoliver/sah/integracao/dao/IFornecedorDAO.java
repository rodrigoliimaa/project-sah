package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface IFornecedorDAO {
	
	void alterar(Fornecedor fornecedor);
	
	Integer inserir(Fornecedor fornecedor);
	
	List<Fornecedor> listarPaginado(PaginacaoVO fornecedor);
	
	Integer totalRegitrosParaPaginacao(Fornecedor fornecedor);

	Fornecedor consultar(Integer sequencial);
	
	List<Fornecedor> listarFornecedores();

	Fornecedor obter(Fornecedor fornecedor);

}
