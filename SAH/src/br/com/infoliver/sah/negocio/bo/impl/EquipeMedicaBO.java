package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.exception.DBException;
import br.com.infoliver.sah.configuracao.exception.TransactionalException;
import br.com.infoliver.sah.configuracao.resource.ResourceConstant;
import br.com.infoliver.sah.configuracao.resource.ResourceUtils;
import br.com.infoliver.sah.configuracao.validacao.ValidadorEntidade;
import br.com.infoliver.sah.integracao.dao.IEquipeMedicaDAO;
import br.com.infoliver.sah.negocio.bo.IEquipeMedicaBO;
import br.com.infoliver.sah.negocio.entity.EquipeMedica;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("equipeMedicaBO")
@TransactionalException
@Transactional(readOnly=true)
public class EquipeMedicaBO implements IEquipeMedicaBO {
	
	@Autowired private IEquipeMedicaDAO equipeMedicaDAO;
	@Autowired private PaginacaoVO paginacaoVO;
	@Autowired private RetornoVO retornoVO;
	@Autowired private ValidadorEntidade validadorEntidade;

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RetornoVO alterar(EquipeMedica equipeMedica) {
		
		try {
			equipeMedicaDAO.alterar(equipeMedica);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException(e);
		}
		
		return retornoVO.retornar(null, ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RetornoVO excluir(EquipeMedica equipeMedica) {
		try {
			equipeMedicaDAO.excluir(equipeMedica);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException(e);
		}
		
		return retornoVO.retornar(null, ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroExcluidoComSucesso));
	}

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RetornoVO inserir(EquipeMedica equipeMedica) {
		
		try {
			equipeMedicaDAO.inserir(equipeMedica);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return retornoVO.retornar(null, ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
	}

	@Override
	public List<EquipeMedica> listar() {
		return equipeMedicaDAO.listar();
	}

	@Override
	public PaginacaoVO listarPaginado(PaginacaoVO equipeMedica) {
		PaginacaoVO paginacaoVO = new PaginacaoVO();
		paginacaoVO.setEntidade(equipeMedicaDAO.listarPaginado(equipeMedica));
		return paginacaoVO;
	}

	@Override
	public EquipeMedica obter(EquipeMedica equipeMedica) {
		return equipeMedicaDAO.obter(equipeMedica);
	}
	
}
