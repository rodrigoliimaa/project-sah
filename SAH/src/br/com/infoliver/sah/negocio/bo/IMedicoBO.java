package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IMedicoBO {
	
	RetornoVO alterar(Medico medico);
	
	Medico consultar(Integer sequencial);

	RetornoVO inserir(Medico medico);

	List<Medico> listar();

	PaginacaoVO listarPaginado(PaginacaoVO medico);

	Medico obter(Medico medico);

	
}