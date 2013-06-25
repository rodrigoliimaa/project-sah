package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.entity.MovimentacaoCID;
import br.com.infoliver.sah.negocio.entity.MovimentacaoProcedimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface IMovimentacaoDAO {
	
	Movimentacao consultarPorNumeroNota(String numeroNota);
	
	List<Movimentacao> listarPaginado(PaginacaoVO movimentacao);
	
	List<Movimentacao> listarRelatorioMapa(Movimentacao movimentacao);
	
	List<Movimentacao> listarRelatorioMapaBpi(Movimentacao movimentacao);
	
	List<Movimentacao> listarRelatorioEncaminhamento(Movimentacao moviemntacao);
	
	List<Movimentacao> listarRelatorioTermoCompromisso(Movimentacao movimentacao);
	
	List<Movimentacao> listarRelatorioEquipamentos(Movimentacao movimentacao);
	
	List<Movimentacao> listarRelatorioEquipamentosSintetico(Movimentacao movimentacao);
	
	Integer totalRegitrosParaPaginacao(Movimentacao movimentacao);

	Integer inserir(Movimentacao movimentacao);

	List<MovimentacaoCID> consultarCids(Integer sequencial);

	List<MovimentacaoProcedimento> consultarProcedimentos(Integer sequencial);
	List<MovimentacaoProcedimento> consultarProcedimentos(Integer sequencial, String cid);

	void alterar(Movimentacao movimentacao);

	Movimentacao consultar(Integer sequencial);

	List<Movimentacao> listarPaginandoParaRelatorio(PaginacaoVO movimentacao);

	void excluirMovimentacao(Movimentacao movimentacao);

	List<Movimentacao> listarPaginandoAuditivo(String tipoPesquisa);

	Integer totalRegitrosParaPaginacaoAuditivo(String tipoPesquisa);

}
