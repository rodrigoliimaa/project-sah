package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Agendamento;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IAgendamentoBO {

	RetornoVO alterarSituacao(Agendamento agendamento);
	
	RetornoVO inserir(Agendamento agendamento);

	PaginacaoVO listarPaginado(PaginacaoVO agendamento);

	RetornoVO reagendarAgendamento(Agendamento agendamento);
	List<Agendamento> listarAgendamentos(Medico medico);
	List<Agendamento> listarAgendamentoRelatorio(String sql);

}
