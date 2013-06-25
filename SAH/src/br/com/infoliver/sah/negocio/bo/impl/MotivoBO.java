package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.exception.TransactionalException;
import br.com.infoliver.sah.configuracao.resource.ResourceConstant;
import br.com.infoliver.sah.configuracao.resource.ResourceUtils;
import br.com.infoliver.sah.configuracao.validacao.ValidadorEntidade;
import br.com.infoliver.sah.integracao.dao.IMotivoDAO;
import br.com.infoliver.sah.negocio.bo.IMotivoBO;
import br.com.infoliver.sah.negocio.entity.Licenca;
import br.com.infoliver.sah.negocio.entity.Motivo;
import br.com.infoliver.sah.negocio.entity.Licenca.InserirLicenca;
import br.com.infoliver.sah.negocio.entity.Motivo.AlterarMotivo;
import br.com.infoliver.sah.negocio.entity.Motivo.ExcluirMotivo;
import br.com.infoliver.sah.negocio.entity.Motivo.InserirMotivo;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("motivoBO")
@TransactionalException
@Transactional(readOnly=true)
public class MotivoBO implements IMotivoBO {
	
	@Autowired private IMotivoDAO motivoDAO;
	@Autowired private RetornoVO retornoVO;
	@Autowired private ValidadorEntidade validadorEntidade;
	
	@Override
	public List<Motivo> listar() {
		try {
			return motivoDAO.listar();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException("Não foi possível listar os MOTIVOS");
		}
	}
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO alterar(Motivo motivo) {
		validadorEntidade.validar(Motivo.class, motivo, AlterarMotivo.class).lancarExcecao();
		
		verificarDescricao(motivo);
		
		try {
			motivoDAO.alterar(motivo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException("Não foi possível alterar o registro");
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO excluir(Motivo motivo) {
		validadorEntidade.validar(Motivo.class, motivo, ExcluirMotivo.class).lancarExcecao();
		
		try {
			motivoDAO.excluir(motivo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException("Não foi possível excluir o registro");
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroExcluidoComSucesso));
	}

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO inserir(Motivo motivo) {
		validadorEntidade.validar(Motivo.class, motivo, InserirMotivo.class).lancarExcecao();
		
		verificarDescricao(motivo);
		
		try {
			motivoDAO.inserir(motivo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException("Não foi possível inserir o registro");
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
	}
	
	private void verificarDescricao(Motivo motivo) {
		if (motivoDAO.recuperarPorDescricao(motivo) != null) {
			throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemErroDadoDuplicado));
		}
	}

}
