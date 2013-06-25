package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Agendamento;
import br.com.infoliver.sah.negocio.entity.Horario;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface IAgendamentoDAO {

	void alterarSituacao(Agendamento agendamento);
	
	Integer inserir(Agendamento agendamento);

	List<Agendamento> listarPaginado(PaginacaoVO agendamento);
	
	List<Agendamento> listarAgendamentos(Medico medico);
	
	Integer totalRegitrosParaPaginacao(Agendamento entidade);
	
	public List<Horario> listarHorariosPorMedico(Medico medico);
	List<Agendamento> listarAgendamentoRelatorio(String sql);
	

}
