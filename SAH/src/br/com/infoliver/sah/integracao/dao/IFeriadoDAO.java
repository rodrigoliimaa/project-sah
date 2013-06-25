package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Feriado;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IFeriadoDAO {

	void alterar(Feriado feriado);
	
	void excluir(Feriado feriado);
	
	Integer inserir(Feriado feriado);

	List<Feriado> listar();

}
