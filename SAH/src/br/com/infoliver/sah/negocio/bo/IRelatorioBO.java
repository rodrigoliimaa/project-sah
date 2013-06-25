package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Relatorio;

public interface IRelatorioBO {
	List<Relatorio> listarRelatorioProcedimento();
	
	Relatorio consultar(Integer sequencial);
}