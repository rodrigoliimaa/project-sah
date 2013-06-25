package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IPacienteBO {
	
	RetornoVO inserir(Paciente paciente)throws BOException;
	
	RetornoVO alterar(Paciente paciente)throws BOException;

	RetornoVO excluir(Paciente paciente)throws BOException;
	
	PaginacaoVO listarPaginado(PaginacaoVO paciente);

	List<Paciente> listar();
	List<Paciente> listarPacienteRelatorio(PaginacaoVO paciente);

	Paciente obter(Paciente paciente);
	
}