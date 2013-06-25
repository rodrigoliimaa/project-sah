package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.ILogAcessoDAO;
import br.com.infoliver.sah.negocio.bo.ILogAcessoBO;
import br.com.infoliver.sah.negocio.entity.LogAcesso;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Service("logAcessoBO")
public class LogAcessoBO implements ILogAcessoBO {
	@Autowired private ILogAcessoDAO logAcessoDAO;
	@Autowired private PaginacaoVO paginacaoVO;
	
	@Override
	public PaginacaoVO listarPaginado(PaginacaoVO logAcesso) {
		Integer totalRegistros=logAcessoDAO.totalRegitrosParaPaginacao(logAcesso);
		List<LogAcesso> lista=logAcessoDAO.listarPaginado(logAcesso);
		paginacaoVO.setEntidade(lista);
		paginacaoVO.setTotalRegistros(totalRegistros);
		return paginacaoVO;
	}
}