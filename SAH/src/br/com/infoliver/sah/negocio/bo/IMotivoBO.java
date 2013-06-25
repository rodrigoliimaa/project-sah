package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Motivo;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IMotivoBO {
	
	RetornoVO alterar(Motivo motivo);
	
	RetornoVO excluir(Motivo motivo);
	
	RetornoVO inserir(Motivo motivo);

	List<Motivo> listar();

}
