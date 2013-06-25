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
import br.com.infoliver.sah.integracao.dao.ILicencaDAO;
import br.com.infoliver.sah.integracao.dao.IMotivoDAO;
import br.com.infoliver.sah.negocio.bo.ILicencaBO;
import br.com.infoliver.sah.negocio.entity.Licenca;
import br.com.infoliver.sah.negocio.entity.Licenca.AlterarLicenca;
import br.com.infoliver.sah.negocio.entity.Licenca.ExcluirLicenca;
import br.com.infoliver.sah.negocio.entity.Licenca.InserirLicenca;
import br.com.infoliver.sah.negocio.entity.Motivo;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("licencaBO")
@TransactionalException
@Transactional(readOnly=true)
public class LicencaBO implements ILicencaBO {
	@Autowired private IMotivoDAO motivoDAO;
	@Autowired private ILicencaDAO licencaDAO;
	@Autowired private PaginacaoVO paginacaoVO;
	@Autowired private RetornoVO retornoVO;
	@Autowired private ValidadorEntidade validadorEntidade;
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO alterar(Licenca licenca) {
		validadorEntidade.validar(Licenca.class, licenca, AlterarLicenca.class).lancarExcecao();
		
		if (licenca.getDataFim() == null) {
			licenca.setDataFim(licenca.getDataInicio());
		}
		
		try {
			licencaDAO.alterar(licenca);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException(e);
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO excluir(Licenca licenca) {
		validadorEntidade.validar(Licenca.class, licenca, ExcluirLicenca.class).lancarExcecao();
		
		try {
			licencaDAO.excluir(licenca);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException(e);
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroExcluidoComSucesso));
	}
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO inserir(Licenca licenca) {
		validadorEntidade.validar(Licenca.class, licenca, InserirLicenca.class).lancarExcecao();
		
		if (licenca.getDataFim() == null) {
			licenca.setDataFim(licenca.getDataInicio());
		}
		
		try {
			licencaDAO.inserir(licenca);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException(e);
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
	}
	
	@Override
	public PaginacaoVO listarPaginado(PaginacaoVO licenca) {
		try {
			Integer totalRegistros = licencaDAO.totalRegitrosParaPaginacao((Licenca) licenca.getEntidade());
			List<Licenca> lista=licencaDAO.listarPaginado(licenca);
			
			// recuperar motivos
			List<Motivo> motivos = motivoDAO.listar();
			
			// incluir motivos nas licenças
			for (Licenca itemLicenca : lista) {
				for (Motivo itemMotivo : motivos) {
					if (itemLicenca.getMotivo().getSequencial().intValue() == itemMotivo.getSequencial().intValue()) {
						itemLicenca.getMotivo().setDescricao(itemMotivo.getDescricao());
						break;
					}
				}
			}
			
			paginacaoVO.setEntidade(lista);
			paginacaoVO.setTotalRegistros(totalRegistros);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paginacaoVO;
	}

}
