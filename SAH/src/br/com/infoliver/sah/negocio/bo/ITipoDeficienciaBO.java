package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.TipoDeficiencia;

public interface ITipoDeficienciaBO {

	List<TipoDeficiencia> listar();
	void inserir(TipoDeficiencia tipoDeficiencia);
	
}
