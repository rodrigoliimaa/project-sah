package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.ISetorDAO;
import br.com.infoliver.sah.negocio.bo.ISetorBO;
import br.com.infoliver.sah.negocio.entity.Setor;

@Service("setorBO")
public class SetorBO implements ISetorBO{

	@Autowired private ISetorDAO setorDAO;
	
	@Override
	public List<Setor> listar() {
		return setorDAO.listar();
	}
}