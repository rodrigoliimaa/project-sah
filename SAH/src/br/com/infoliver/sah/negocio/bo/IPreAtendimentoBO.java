package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.negocio.entity.PreAtendimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IPreAtendimentoBO {

	RetornoVO inserirPreAtendimento(PreAtendimento preAtendimento) throws BOException;
	void alterar(PreAtendimento preAtendimento) throws BOException;
	void excluir(PreAtendimento preAtendimento) throws BOException;
	Integer totalRegitrosParaPaginacao(PaginacaoVO preAtendimento);

	List<PreAtendimento> listarPaginado(PaginacaoVO preAtendimento);
	List<PreAtendimento> listarEncaminhamentoExterno(PreAtendimento preAtendimento);
	
	PaginacaoVO listarPreAtendimentoAtendidos(PaginacaoVO preAtendimento);	
	PaginacaoVO listarPreAtendimentoPaginado(PaginacaoVO preAtendimento);
	PaginacaoVO listarPaginadoFiltro(PaginacaoVO preAtendimento);
	PaginacaoVO listar(PaginacaoVO preAtendimento);
	
	
}
