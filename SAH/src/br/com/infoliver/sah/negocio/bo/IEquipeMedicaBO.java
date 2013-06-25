package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.EquipeMedica;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IEquipeMedicaBO {

	RetornoVO alterar(EquipeMedica equipeMedica);
	
	RetornoVO excluir(EquipeMedica equipeMedica);
	
	RetornoVO inserir(EquipeMedica equipeMedica);

	List<EquipeMedica> listar();
	
	PaginacaoVO listarPaginado(PaginacaoVO equipeMedica);
	
	EquipeMedica obter(EquipeMedica equipeMedica);
	
}
