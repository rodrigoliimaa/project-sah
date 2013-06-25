package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.ISetorEncaminhamentoExternoDAO;
import br.com.infoliver.sah.negocio.bo.ISetorEncaminhamentoExternoBO;
import br.com.infoliver.sah.negocio.entity.SetorEncaminhamentoExterno;

@Service("setorEncaminhamentoExternoBO")
public class SetorEncaminhamentoExternoBO implements ISetorEncaminhamentoExternoBO {
	
	@Autowired private ISetorEncaminhamentoExternoDAO setorEncaminhamentoDAO;

	@Override
	public List<SetorEncaminhamentoExterno> listarSetor() {
		
		return setorEncaminhamentoDAO.listarSetor();
	}

	@Override
	public void inserirSetor(SetorEncaminhamentoExterno encaminhamentoExterno) {
		setorEncaminhamentoDAO.inserir(encaminhamentoExterno);
		
		
	}

	

}
