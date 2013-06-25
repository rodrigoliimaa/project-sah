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
import br.com.infoliver.sah.integracao.dao.IMedicoDAO;
import br.com.infoliver.sah.negocio.bo.IMedicoBO;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Medico.AlterarMedico;
import br.com.infoliver.sah.negocio.entity.Medico.InserirMedico;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("medicoBO")
@TransactionalException
@Transactional(readOnly=true)
public class MedicoBO implements IMedicoBO {
	@Autowired private IMedicoDAO medicoDAO;
	@Autowired private PaginacaoVO paginacaoVO;
	@Autowired private RetornoVO retornoVO;
	@Autowired private ValidadorEntidade validadorEntidade;
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RetornoVO alterar(Medico medico) {
		validadorEntidade.validar(Medico.class,medico, InserirMedico.class, AlterarMedico.class).lancarExcecao();
		
		// validar cpf único
		Medico medicoRecuperadoPorCpf = medicoDAO.consultarPorCpf(medico.getCpf());
		if (medicoRecuperadoPorCpf != null && medicoRecuperadoPorCpf.getSequencial().intValue() != medico.getSequencial().intValue()) {
			throw new BOException(ResourceUtils.getResourceFromKey(
					ResourceConstant.mensagemErroViolacaoUnique, "CPF",
					"VALOR (" + medico.getCpf() + ")"));
		}
		
		// validar rg e orgão expedidor (combinação única)
//		Medico medicoRecuperadoPorRg = medicoDAO.consultarPorRg(medico.getRg(), medico.getOrgaoExpedidor());
//		if (medicoRecuperadoPorRg != null && medicoRecuperadoPorRg.getSequencial().intValue() != medico.getSequencial().intValue()) {
//				throw new BOException(ResourceUtils.getResourceFromKey(
//						ResourceConstant.mensagemErroViolacaoUnique, "A COMBINAÇÃO RG E ORGÃO EXPEDIDOR",
//						"VALOR (" + medico.getRg()  + ", " + medico.getOrgaoExpedidor()+ ")"));
//		}
		
		// validar cns único
		Medico medicoRecuperadoPorCns = medicoDAO.consultarPorCns(medico.getCns());
		if (medicoRecuperadoPorCns != null && medicoRecuperadoPorCns.getSequencial().intValue() != medico.getSequencial().intValue()) {
			throw new BOException(ResourceUtils.getResourceFromKey(
					ResourceConstant.mensagemErroViolacaoUnique, "CNS",
					"VALOR (" + medico.getCns() + ")"));
		}
		
//		medicoDAO.alterar(medico);
		try {
			medicoDAO.alterar(medico);
//			Integer sequencial = medicoDAO.inserir(medico);
//			medico.setSequencial(sequencial);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}
	
	@Override
	public Medico consultar(Integer sequencial) {
		return medicoDAO.consultar(sequencial);
	}

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RetornoVO inserir(Medico medico) {
		validadorEntidade.validar(Medico.class,medico,InserirMedico.class).lancarExcecao();
		
		// validar cpf único
		if (medicoDAO.consultarPorCpf(medico.getCpf()) != null) {
			throw new BOException(ResourceUtils.getResourceFromKey(
					ResourceConstant.mensagemErroViolacaoUnique, "CPF",
					"VALOR (" + medico.getCpf() + ")"));
		}
		
		// validar rg e orgão expedidor (combinação única)
//		Medico medicoConsultado = medicoDAO.consultarPorRg(medico.getRg(), medico.getOrgaoExpedidor());
//		if (medicoConsultado != null) {
////			if (medico.getOrgaoExpedidor().equals(medicoConsultado.getOrgaoExpedidor())) {
//				throw new BOException(ResourceUtils.getResourceFromKey(
//						ResourceConstant.mensagemErroViolacaoUnique, "A COMBINAÇÃO RG E ORGÃO EXPEDIDOR",
//						"VALOR (" + medico.getRg()  + ", " + medico.getOrgaoExpedidor()+ ")"));
////			}
//		}
		
		// validar cns único
		if (medicoDAO.consultarPorCns(medico.getCns()) != null) {
			throw new BOException(ResourceUtils.getResourceFromKey(
					ResourceConstant.mensagemErroViolacaoUnique, "CNS",
					"VALOR (" + medico.getCns() + ")"));
		}
		
		try {
			medicoDAO.inserir(medico);
//			Integer sequencial = medicoDAO.inserir(medico);
//			medico.setSequencial(sequencial);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
	}
	
	@Override
	public List<Medico> listar() {
		return medicoDAO.listar();
	}

	@Override
	public PaginacaoVO listarPaginado(PaginacaoVO medico) {
		Integer totalRegistros = medicoDAO.totalRegitrosParaPaginacao((Medico) medico.getEntidade());
		List<Medico> lista=medicoDAO.listarPaginado(medico);
		paginacaoVO.setEntidade(lista);
		paginacaoVO.setTotalRegistros(totalRegistros);
		return paginacaoVO;
	}
	
	@Override
	public Medico obter(Medico medico) {
		if (medico == null) {
			throw new BOException("O MÉDICO não deveria ser nulo");
		}
		
		if (medico.getSequencial() == null) {
			throw new BOException("O CÓDIGO do MÉDICO  não deveria ser nulo");
		}
		
		Medico medicoRecuperado = null;
		try {
			medicoRecuperado = medicoDAO.obter(medico);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException("Não foi possível recuperar o médico");
		}
		
		if (medicoRecuperado == null) {
			throw new BOException("Nenhum médico encontrado para o código " + medico.getSequencial());
		}
		
		return medicoRecuperado;
	}
}