package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface IPacienteDAO {
	
	
	void alterar(Paciente paciente)throws DAOException;

	Paciente consultar(Integer sequencial);
	
	void excluir(Paciente paciente)throws DAOException;
	
	Integer inserir(Paciente paciente)throws DAOException;
	
	List<Paciente> listarPaginado(PaginacaoVO paciente);
	
	List<Paciente> listar();
	
	List<Paciente> listarPacienteRelatorio(PaginacaoVO paciente);
	
	Integer totalRegitrosParaPaginacao(PaginacaoVO paciente);

	Paciente obter(Paciente paciente);


}