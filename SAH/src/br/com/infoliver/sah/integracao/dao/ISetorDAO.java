package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Setor;

public interface ISetorDAO {
	
	List<Setor> listar();	
	Integer inserirSetor(Setor setor);
}