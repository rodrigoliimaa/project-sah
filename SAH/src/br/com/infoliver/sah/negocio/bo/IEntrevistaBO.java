package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.negocio.entity.Entrevista;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IEntrevistaBO {
	
	RetornoVO inserirEntrevista(Entrevista entrevista) throws BOException;
	void alterar(Entrevista entrevista) throws BOException;
	void excluir(Entrevista entrevista) throws BOException;
	Integer totalRegitrosParaPaginacao(PaginacaoVO entrevista);

	List<Entrevista> listarPaginado(PaginacaoVO entrevista);	
	PaginacaoVO listar(PaginacaoVO entrevista);

}
