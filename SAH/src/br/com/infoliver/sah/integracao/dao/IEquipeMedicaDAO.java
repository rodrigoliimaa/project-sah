package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.EquipeMedica;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface IEquipeMedicaDAO {

	void alterar(EquipeMedica equipeMedica);
	
	void excluir(EquipeMedica equipeMedica);
	
	Integer inserir(EquipeMedica equipeMedica);

	List<EquipeMedica> listar();
	
	List<EquipeMedica> listarPaginado(PaginacaoVO equipeMedica);
	
	EquipeMedica obter(EquipeMedica equipeMedica);
	
}
