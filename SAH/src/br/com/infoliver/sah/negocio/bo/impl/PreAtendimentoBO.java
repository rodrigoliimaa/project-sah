package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.exception.DBException;
import br.com.infoliver.sah.configuracao.exception.TransactionalException;
import br.com.infoliver.sah.configuracao.resource.ResourceConstant;
import br.com.infoliver.sah.configuracao.resource.ResourceUtils;
import br.com.infoliver.sah.integracao.dao.IPreAtendimentoDAO;
import br.com.infoliver.sah.negocio.bo.IPreAtendimentoBO;
import br.com.infoliver.sah.negocio.entity.PreAtendimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;
@Service("preAtendimentoBO")
@TransactionalException
@Transactional(readOnly=true)
public class PreAtendimentoBO implements IPreAtendimentoBO {
	
	@Autowired private IPreAtendimentoDAO preAtendimentoDAO;
	@Autowired private RetornoVO retornoVO;
	@Autowired private PaginacaoVO paginacaoVO;

	
	@Override
	@Transactional(readOnly=false)
	public RetornoVO inserirPreAtendimento(PreAtendimento preAtendimento) throws BOException {
		try {
			preAtendimentoDAO.inserir(preAtendimento);
			return retornoVO.retornar(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
		} catch (Exception e) {
			throw new DBException(e);
		}		
	}

	
	@Override
	@Transactional(readOnly=false)
	public void alterar(PreAtendimento preAtendimento) throws BOException {
		preAtendimentoDAO.alterar(preAtendimento);
		
	}

	@Override
	@Transactional(readOnly=false)
	public void excluir(PreAtendimento preAtendimento) throws BOException {
		preAtendimentoDAO.excluir(preAtendimento);
		
	}

	@Override
	public Integer totalRegitrosParaPaginacao(PaginacaoVO paciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PreAtendimento> listarPaginado(PaginacaoVO preAtendimento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginacaoVO listarPreAtendimentoAtendidos(PaginacaoVO preAtendimento) {
		
		Integer totalRegistros=preAtendimentoDAO.totalRegitrosParaPaginar();		
		List<PreAtendimento> lista=preAtendimentoDAO.listarPreAtendimentoAtendidos(preAtendimento);
		paginacaoVO.setEntidade(lista);
		paginacaoVO.setTotalRegistros(totalRegistros);
		return paginacaoVO;
	}

	@Override
	public PaginacaoVO listarPreAtendimentoPaginado(PaginacaoVO preAtendimento) {
		Integer totalRegistros=preAtendimentoDAO.totalRegitrosParaPaginar();
		List<PreAtendimento> lista=preAtendimentoDAO.listarPreAtendimentoPaginado(preAtendimento);
		paginacaoVO.setEntidade(lista);
		paginacaoVO.setTotalRegistros(totalRegistros);
		return paginacaoVO;
	}

	@Override
	public PaginacaoVO listarPaginadoFiltro(PaginacaoVO preAtendimento) {
		Integer totalRegistros=preAtendimentoDAO.totalRegitrosParaPaginar();		
		List<PreAtendimento> lista=preAtendimentoDAO.listarPreAtendimentoPaginadoFiltro(preAtendimento);		
		paginacaoVO.setEntidade(lista);
		paginacaoVO.setTotalRegistros(totalRegistros);
		return paginacaoVO;
	}


	@Override
	@Transactional(readOnly=false)
	public PaginacaoVO listar(PaginacaoVO preAtendimento) {
		Integer totalRegistros=preAtendimentoDAO.totalRegitrosParaPaginar();
		List<PreAtendimento> lista=preAtendimentoDAO.listar(preAtendimento);
		paginacaoVO.setEntidade(lista);
		paginacaoVO.setTotalRegistros(totalRegistros);
		return paginacaoVO;
	}
	@Override
	@Transactional(readOnly=false)
	public  List<PreAtendimento> listarEncaminhamentoExterno(PreAtendimento preAtendimento) {
		Integer totalRegistros=preAtendimentoDAO.totalRegitrosParaPaginar();
		List<PreAtendimento> lista=preAtendimentoDAO.listarEncaminhamentoExterno(preAtendimento);	
		paginacaoVO.setEntidade(lista);
		paginacaoVO.setTotalRegistros(totalRegistros);
		return lista;
	}
	

}
