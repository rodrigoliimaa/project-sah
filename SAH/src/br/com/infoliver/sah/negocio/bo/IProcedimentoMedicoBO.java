package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.dto.CodigoDescricaoProcedimentoDTO;
import br.com.infoliver.sah.negocio.entity.ProcedimentoMedico;
import br.com.infoliver.sah.negocio.vo.ProcedimentoMedicoVO;

public interface IProcedimentoMedicoBO {
	List<ProcedimentoMedico> listarTipoProcedimentoMedico();
	
	ProcedimentoMedicoVO consultarProcedimentoMedico(Integer seqGrupoLaudo,Integer seqProcedimentoMedico,Integer seqMedico,Integer seqRelatorio);
	
	List<ProcedimentoMedico> listarProcedimentoMedicoPrincipal();
	
	List<ProcedimentoMedico> listarProcedimentoMedicoPrincipalPoliclinica();
	
	List<ProcedimentoMedico> listarProcedimentoMedicoSecundario();

	CodigoDescricaoProcedimentoDTO consultarCodigoProcedimentoPrincipal(Integer seqProcedimentoPrincipalPoliclinica);
}