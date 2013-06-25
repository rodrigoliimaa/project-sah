package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.dto.CodigoDescricaoProcedimentoDTO;
import br.com.infoliver.sah.negocio.entity.ProcedimentoMedico;

public interface IProcedimentoMedicoDAO {
	List<ProcedimentoMedico> listarTipoProcedimentoMedico();
	
	ProcedimentoMedico consultarTipoProcedimentoMedico(Integer sequencial);
	
	List<ProcedimentoMedico> listarProcedimentoMedicoPrincipal();
	
	List<ProcedimentoMedico> listarProcedimentoMedicoPrincipalPoliclinica();
	
	List<ProcedimentoMedico> listarProcedimentoMedicoSecundario();

	CodigoDescricaoProcedimentoDTO consultarCodigoProcedimentoPrincipal(Integer seqProcedimentoPrincipalPoliclinica);
}