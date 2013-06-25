package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.ITipoResponsavelDAO;
import br.com.infoliver.sah.negocio.bo.ITipoResponsavelBO;
import br.com.infoliver.sah.negocio.entity.TipoResponsavel;

@Service("tipoResponsavelBO")
public class TipoResponsavelBO implements ITipoResponsavelBO {

	@Autowired	private ITipoResponsavelDAO tipoResponsavelDAO;
	
	@Override
	public List<TipoResponsavel> listar() {
		return tipoResponsavelDAO.listar();
	}
}