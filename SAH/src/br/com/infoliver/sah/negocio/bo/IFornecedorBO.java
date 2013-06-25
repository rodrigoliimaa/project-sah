package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IFornecedorBO {

	RetornoVO alterar(Fornecedor fornecedor);
	
	RetornoVO inserir(Fornecedor fornecedor);

	PaginacaoVO listarPaginado(PaginacaoVO fornecedor);
	
	List<Fornecedor> listarFornecedores();

	Fornecedor obter(Fornecedor fornecedor);

}
