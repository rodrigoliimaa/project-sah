package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.ITipoDeficienciaDAO;
import br.com.infoliver.sah.negocio.bo.ITipoDeficienciaBO;
import br.com.infoliver.sah.negocio.entity.TipoDeficiencia;

@Service("tipoDeficienciaBO")
public class TipoDeficienciaBO implements ITipoDeficienciaBO {

	@Autowired private ITipoDeficienciaDAO tipoDeficienciaDAO;
	
	@Override
	public List<TipoDeficiencia> listar() {
		return tipoDeficienciaDAO.listar();
	}

	@Override
	public void inserir(TipoDeficiencia tipoDeficiencia) {
		tipoDeficienciaDAO.inserir(tipoDeficiencia);
		
	}

}
