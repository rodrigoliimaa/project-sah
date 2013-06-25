package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.SigtapCID;
import br.com.infoliver.sah.negocio.entity.SigtapProcedimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface ISigtapBO {
	
	PaginacaoVO listarProcedimentoPaginado(PaginacaoVO sigtapProcedimento);

	PaginacaoVO listarCIDPaginado(PaginacaoVO sigtapCID);
	
	List<SigtapProcedimento> listarProcedimentos();

	SigtapCID obterCid(SigtapCID sigtapCID);

	SigtapProcedimento obterProcedimento(SigtapProcedimento sigtapProcedimento);

	SigtapProcedimento obterSigtapProcedimentoParaConsulta(String codigo);

}
