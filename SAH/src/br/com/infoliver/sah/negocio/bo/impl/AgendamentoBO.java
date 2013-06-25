package br.com.infoliver.sah.negocio.bo.impl;

import java.util.ArrayList;
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
import br.com.infoliver.sah.integracao.dao.IAgendamentoDAO;
import br.com.infoliver.sah.integracao.dao.ILicencaDAO;
import br.com.infoliver.sah.negocio.bo.IAgendamentoBO;
import br.com.infoliver.sah.negocio.entity.Agendamento;
import br.com.infoliver.sah.negocio.entity.Agendamento.AlterarSituacaoAgendamento;
import br.com.infoliver.sah.negocio.entity.Agendamento.InserirAgendamento;
import br.com.infoliver.sah.negocio.entity.Horario;
import br.com.infoliver.sah.negocio.entity.Licenca;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("agendamentoBO")
@TransactionalException
@Transactional(readOnly=true)
public class AgendamentoBO implements IAgendamentoBO {
	
	@Autowired private IAgendamentoDAO agendamentoDAO;
	@Autowired private ILicencaDAO licencaDAO;
	@Autowired private PaginacaoVO paginacaoVO;
	@Autowired private RetornoVO retornoVO;
	@Autowired private ValidadorEntidade validadorEntidade;
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO alterarSituacao(Agendamento agendamento) {
		validadorEntidade.validar(Agendamento.class, agendamento, AlterarSituacaoAgendamento.class).lancarExcecao();
		
		try {
			agendamentoDAO.alterarSituacao(agendamento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return retornoVO.retornar(null, ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO inserir(Agendamento agendamento) {
		validadorEntidade.validar(Agendamento.class, agendamento, InserirAgendamento.class).lancarExcecao();
		
		try {
			agendamentoDAO.inserir(agendamento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return retornoVO.retornar(null, ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
	}
	
	@Override
	public PaginacaoVO listarPaginado(PaginacaoVO agendamento) {
		try {
			Integer totalRegistros = agendamentoDAO.totalRegitrosParaPaginacao((Agendamento) agendamento.getEntidade());
			List<Agendamento> lista = agendamentoDAO.listarPaginado(agendamento);
			for (int i = 0; i < lista.size(); i++) {
				lista.get(i).getMedico().setHorarios(new ArrayList<Horario>());
				List<Horario> horarios = agendamentoDAO.listarHorariosPorMedico(
						lista.get(i).getMedico()
				);
				if (horarios != null) {
					lista.get(i).getMedico().setHorarios(horarios);
				}
				List<Licenca> licencas = licencaDAO.listarPorMedico(
						lista.get(i).getMedico()
				);
				if (licencas != null) {
					lista.get(i).getMedico().setLicencas(licencas);
				}
			}
			paginacaoVO.setEntidade(lista);
			paginacaoVO.setTotalRegistros(totalRegistros);
			return paginacaoVO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException(e);
		}
	}
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO reagendarAgendamento(Agendamento agendamento) {
		validadorEntidade.validar(Agendamento.class, agendamento, AlterarSituacaoAgendamento.class, InserirAgendamento.class).lancarExcecao();
		
		try {
			agendamento.setSituacao(Agendamento.SITUACAO_AGENDADO);
			
			Integer reagendamentoSequencial = agendamentoDAO.inserir(agendamento);
			Agendamento reagendamento = new Agendamento();
			reagendamento.setSequencial(reagendamentoSequencial);
			
			agendamento.setSituacao(Agendamento.SITUACAO_REAGENDAMENTO);
			agendamento.setReagendamento(reagendamento);
			agendamentoDAO.alterarSituacao(agendamento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException(e);
		}
		
		return retornoVO.retornar(null, ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}

	@Override
	public List<Agendamento> listarAgendamentos(Medico medico) {
		// TODO Auto-generated method stub
		return agendamentoDAO.listarAgendamentos(medico);
	}

	@Override
	public List<Agendamento> listarAgendamentoRelatorio(String sql) {
		return agendamentoDAO.listarAgendamentoRelatorio(sql);
	}
		

}
