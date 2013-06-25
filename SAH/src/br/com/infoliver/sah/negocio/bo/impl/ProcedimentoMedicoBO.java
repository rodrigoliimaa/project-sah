package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.IProcedimentoMedicoDAO;
import br.com.infoliver.sah.negocio.bo.IGrupoLaudoBO;
import br.com.infoliver.sah.negocio.bo.IMedicoBO;
import br.com.infoliver.sah.negocio.bo.IProcedimentoMedicoBO;
import br.com.infoliver.sah.negocio.bo.IRelatorioBO;
import br.com.infoliver.sah.negocio.dto.CodigoDescricaoProcedimentoDTO;
import br.com.infoliver.sah.negocio.entity.GrupoLaudo;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.entity.ProcedimentoMedico;
import br.com.infoliver.sah.negocio.entity.Relatorio;
import br.com.infoliver.sah.negocio.vo.ProcedimentoMedicoVO;
import br.com.infoliver.sah.negocio.vo.RelatorioProcedimentoMedicoVO;
import flex.messaging.io.ArrayList;

@Service("procedimentoMedicoBO")
public class ProcedimentoMedicoBO implements IProcedimentoMedicoBO {
	@Autowired	private IProcedimentoMedicoDAO procedimentoMedicoDAO;
	@Autowired	private IGrupoLaudoBO grupoLaudoBO;
	@Autowired	private IMedicoBO medicoBO;
	@Autowired	private IRelatorioBO relatorioBO;
	
	@Override
	public List<ProcedimentoMedico> listarTipoProcedimentoMedico() {
		return procedimentoMedicoDAO.listarTipoProcedimentoMedico();
	}

	@Override
	public ProcedimentoMedicoVO consultarProcedimentoMedico(
			Integer seqGrupoLaudo, Integer seqProcedimentoMedico,
			Integer seqMedico, Integer seqRelatorio) {
		List<Paciente> listarPaciente = grupoLaudoBO.listarPacienteGrupoLaudo(new GrupoLaudo(seqGrupoLaudo));
		ProcedimentoMedico procedimentoMedico = procedimentoMedicoDAO.consultarTipoProcedimentoMedico(seqProcedimentoMedico);
		Medico medico = medicoBO.consultar(seqMedico);
		Relatorio relatorio = relatorioBO.consultar(seqRelatorio);
		//-------------------------------------------------------------------
		List<RelatorioProcedimentoMedicoVO> lista=new ArrayList();
		for (Paciente paciente : listarPaciente)
			lista.add(new RelatorioProcedimentoMedicoVO(paciente,procedimentoMedico,medico));
				
		ProcedimentoMedicoVO procedimentoMedicoVO=new ProcedimentoMedicoVO(lista,relatorio.getNomeArquivo());
		return procedimentoMedicoVO;
	}
	
	@Override
	public CodigoDescricaoProcedimentoDTO consultarCodigoProcedimentoPrincipal(Integer seqProcedimentoPrincipalPoliclinica) {
		return procedimentoMedicoDAO.consultarCodigoProcedimentoPrincipal(seqProcedimentoPrincipalPoliclinica);
	}

	@Override
	public List<ProcedimentoMedico> listarProcedimentoMedicoPrincipal() {
		return procedimentoMedicoDAO.listarProcedimentoMedicoPrincipal();
	}

	@Override
	public List<ProcedimentoMedico> listarProcedimentoMedicoSecundario() {
		return procedimentoMedicoDAO.listarProcedimentoMedicoSecundario();
	}

	@Override
	public List<ProcedimentoMedico> listarProcedimentoMedicoPrincipalPoliclinica() {
		return procedimentoMedicoDAO.listarProcedimentoMedicoPrincipalPoliclinica();
	}
}