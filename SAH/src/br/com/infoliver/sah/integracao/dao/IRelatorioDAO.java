package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Relatorio;

public interface IRelatorioDAO {
	List<Relatorio> listarRelatorioProcedimento();
	
	Relatorio consultar(Integer sequencial);
}