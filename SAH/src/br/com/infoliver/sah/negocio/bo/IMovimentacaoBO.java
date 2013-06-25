package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IMovimentacaoBO {
	
	RetornoVO inserir(Movimentacao movimentacao);
	
	PaginacaoVO listarPaginado(PaginacaoVO movimentacao);

	PaginacaoVO listarPaginadoParaRelatorio(PaginacaoVO movimentacao);

	RetornoVO alterar(Movimentacao movimentacao);
	
	List<Movimentacao> listarRelatorioMapa(Movimentacao moviemntacao);
	
	List<Movimentacao> listarRelatorioEncaminhamento(Movimentacao moviemntacao);
	
	List<Movimentacao> listarRelatorioTermoCompromisso(Movimentacao movimentacao);
	
	List<Movimentacao> listarRelatorioEquipamentos(Movimentacao movimentacao);
	List<Movimentacao> listarRelatorioEquipamentosSintetico(Movimentacao movimentacao);
	
	List<Movimentacao> listarRelatorioMapaBpi(Movimentacao movimentacao);

	void excluirMovimentacao(Movimentacao movimentacao);

	PaginacaoVO listarPaginandoAuditivo(String tipoPesquisa);

}
