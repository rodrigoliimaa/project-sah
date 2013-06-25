package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface IMedicoDAO {
	
	void alterar(Medico medico);
	
	Medico consultar(Integer sequencial);
	
	Medico consultarPorCns(String cns);
	
	Medico consultarPorCpf(String cpf);

	Integer inserir(Medico medico) throws DAOException;

	Medico consultarPorRg(String rg, String orgaoExpedidor);
	
	List<Medico> listar();

	List<Medico> listarPaginado(PaginacaoVO medico);
	
	Integer totalRegitrosParaPaginacao(Medico medico);

	Medico consultarPorSequencial(Integer sequencial);

	Medico obter(Medico medico);


}