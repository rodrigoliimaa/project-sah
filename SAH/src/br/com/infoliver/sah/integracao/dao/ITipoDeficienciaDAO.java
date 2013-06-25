package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.TipoDeficiencia;

public interface ITipoDeficienciaDAO {

	List<TipoDeficiencia> listar();
	void inserir(TipoDeficiencia tipoDeficiencia);
	
}
