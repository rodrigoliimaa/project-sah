package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.exception.TransactionalException;
import br.com.infoliver.sah.integracao.dao.ISigtapDAO;
import br.com.infoliver.sah.negocio.bo.ISigtapBO;
import br.com.infoliver.sah.negocio.entity.SigtapCID;
import br.com.infoliver.sah.negocio.entity.SigtapProcedimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Service("sigtapBO")
@TransactionalException
@Transactional(readOnly=true)
public class SigtapBO implements ISigtapBO {
	@Autowired private ISigtapDAO sigtapDAO;
	@Autowired private PaginacaoVO paginacaoVO;
//	@Autowired private RetornoVO retornoVO;
//	@Autowired private ValidadorEntidade validadorEntidade;
	
	@Override
	public PaginacaoVO listarCIDPaginado(PaginacaoVO sigtapCID) {
		try {
			Integer totalRegistros = sigtapDAO.totalRegitrosCIDParaPaginacao((SigtapCID) sigtapCID.getEntidade());
			List<SigtapCID> lista= sigtapDAO.listarCIDPaginado(sigtapCID);
			paginacaoVO.setEntidade(lista);
			paginacaoVO.setTotalRegistros(totalRegistros);
			return paginacaoVO;
		} catch (Exception e) {
			throw new BOException(e);
		}
	}
	
	@Override
	public PaginacaoVO listarProcedimentoPaginado(PaginacaoVO sigtapProcedimento) {
		try {
			Integer totalRegistros = sigtapDAO.totalRegitrosProcedimentoParaPaginacao((SigtapProcedimento) sigtapProcedimento.getEntidade());
			List<SigtapProcedimento> lista= sigtapDAO.listarProcedimentoPaginado(sigtapProcedimento);
			paginacaoVO.setEntidade(lista);
			paginacaoVO.setTotalRegistros(totalRegistros);
			return paginacaoVO;
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	@Override
	public List<SigtapProcedimento> listarProcedimentos() {
		try {
			
			List<SigtapProcedimento> lista= sigtapDAO.listarProcedimentos();
			
			return lista;
		} catch (Exception e) {
			throw new BOException(e);
		}
	}
	
	@Override
	public SigtapCID obterCid(SigtapCID sigtapCID) {
		if (sigtapCID == null) {
			throw new BOException("O CID não deveria ser nulo");
		}
		
		if (sigtapCID.getCo_cid() == null) {
			throw new BOException("O CÓDIGO do CID não deveria ser nulo");
		}
		

		SigtapCID sigtapCIDRecuperado = null;
		try {
			sigtapCIDRecuperado = sigtapDAO.obterCid(sigtapCID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException("Não foi possível recuperar o CID");
		}
		
		if (sigtapCIDRecuperado == null) {
			throw new BOException("Nenhum CID encontrado para o código " + sigtapCID.getCo_cid());
		}
		
		return sigtapCIDRecuperado;
	}
	
	@Override
	public SigtapProcedimento obterProcedimento(
			SigtapProcedimento sigtapProcedimento) {
		if (sigtapProcedimento == null) {
			throw new BOException("O PROCEDIMENTO não deveria ser nulo");
		}
		
		if (sigtapProcedimento.getCo_procedimento() == null) {
			throw new BOException("O CÓDIGO do PROCEDIMENTO não deveria ser nulo");
		}
		
		SigtapProcedimento sigtapProcedimentoRecuperado = null;
		try {
			sigtapProcedimentoRecuperado = sigtapDAO.obterProcedimento(sigtapProcedimento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException("Não foi possível recuperar o PROCEDIMENTO");
		}
		
		if (sigtapProcedimentoRecuperado == null) {
			throw new BOException("Nenhum procedimento encontrado para o número " + sigtapProcedimento.getCo_procedimento());
		}
		
		return sigtapProcedimentoRecuperado;
	}

	@Override
	public SigtapProcedimento obterSigtapProcedimentoParaConsulta(String codigo) {
		SigtapProcedimento sigtapProcedimentoRecuperado = sigtapDAO.obterSigtapProcedimentoParaConsulta(codigo);
		
		if (sigtapProcedimentoRecuperado == null){
			throw new BOException("Nenhum procedimento foi encontrado para o código " + codigo);
		}
		
		return sigtapProcedimentoRecuperado;
	}

}
