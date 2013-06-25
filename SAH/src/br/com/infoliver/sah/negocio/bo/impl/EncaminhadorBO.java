package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.IEncaminhadorDAO;
import br.com.infoliver.sah.negocio.bo.IEncaminhadorBO;
import br.com.infoliver.sah.negocio.entity.Encaminhador;

@Service("encaminhadorBO")
public class EncaminhadorBO implements IEncaminhadorBO{

	@Autowired private IEncaminhadorDAO encaminhadorDAO;
	
	@Override
	public List<Encaminhador> listar() {
		return encaminhadorDAO.listar();
	}
}