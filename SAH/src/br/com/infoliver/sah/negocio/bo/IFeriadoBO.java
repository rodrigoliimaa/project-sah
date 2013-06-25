package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Feriado;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IFeriadoBO {

	RetornoVO alterar(Feriado feriado);
	
	RetornoVO excluirFeriado(Feriado feriado);
	
	RetornoVO inserir(Feriado feriado);

	List<Feriado> listar();

}
