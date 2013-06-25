package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.IEscolaridadeDAO;
import br.com.infoliver.sah.negocio.bo.IEscolaridadeBO;
import br.com.infoliver.sah.negocio.entity.Escolaridade;

@Service("escolaridadeBO")
public class EscolaridadeBO implements IEscolaridadeBO{

	@Autowired private IEscolaridadeDAO escolaridadeDAO;
	
	@Override
	public List<Escolaridade> listar() {
		return escolaridadeDAO.listar();
	}
}