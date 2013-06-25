package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.IRacaDAO;
import br.com.infoliver.sah.negocio.bo.IRacaBO;
import br.com.infoliver.sah.negocio.entity.Raca;

@Service("racaBO")
public class RacaBO implements IRacaBO{

	@Autowired private IRacaDAO racaDAO;
	
	@Override
	public List<Raca> listar() {
		return racaDAO.listar();
	}
}