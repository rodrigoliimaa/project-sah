package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Motivo;

public interface IMotivoDAO {

	void alterar(Motivo motivo);
	
	void excluir(Motivo motivo);
	
	void inserir(Motivo motivo);
	
	List<Motivo> listar();

	Motivo recuperarPorDescricao(Motivo motivo);
	
}
