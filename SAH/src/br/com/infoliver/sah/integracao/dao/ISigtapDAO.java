package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.SigtapCID;
import br.com.infoliver.sah.negocio.entity.SigtapProcedimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface ISigtapDAO {

	String consultarDtCompetencia();
	
	SigtapCID consultarCid(String codigo);
	List<SigtapCID> listarCIDPaginado(PaginacaoVO sigtapCID);
	Integer totalRegitrosCIDParaPaginacao(SigtapCID sigtapCID);
	
	SigtapProcedimento consultarProcedimento(String codigo);
	List<SigtapProcedimento> listarProcedimentoPaginado(PaginacaoVO sigtapProcedimento);
	Integer totalRegitrosProcedimentoParaPaginacao(SigtapProcedimento sigtapProcedimento);
	
	List<SigtapProcedimento> listarProcedimentos();

	SigtapCID obterCid(SigtapCID sigtapCID);

	SigtapProcedimento obterProcedimento(SigtapProcedimento sigtapProcedimento);

	SigtapProcedimento obterSigtapProcedimentoParaConsulta(String codigo);

}
