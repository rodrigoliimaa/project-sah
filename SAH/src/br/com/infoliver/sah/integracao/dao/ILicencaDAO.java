package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Licenca;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface ILicencaDAO {

	void alterar(Licenca licenca);
	
	void excluir(Licenca licenca);
	
	Integer inserir(Licenca licenca);
	
	List<Licenca> listarPaginado(PaginacaoVO licenca);

	List<Licenca> listarPorMedico(Medico medico);
	
	Integer totalRegitrosParaPaginacao(Licenca licenca);


}
