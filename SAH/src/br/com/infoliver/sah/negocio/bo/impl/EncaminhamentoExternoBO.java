package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.IEncaminhamentoExternoDAO;
import br.com.infoliver.sah.integracao.dao.IPreAtendimentoDAO;
import br.com.infoliver.sah.negocio.bo.IEncaminhamentoExternoBO;
import br.com.infoliver.sah.negocio.entity.EncaminhamentoExterno;

@Service("encaminhamentoExternoBO")
public class EncaminhamentoExternoBO implements IEncaminhamentoExternoBO {
	
	@Autowired private IEncaminhamentoExternoDAO encaminhamentoDAO;

	@Override
	public List<EncaminhamentoExterno> listarEncaminhamento() {
		return encaminhamentoDAO.listarEncaminhamento();
	}

	@Override
	public void inserirEncaminhamento(EncaminhamentoExterno encaminhamentoExterno) {
		 encaminhamentoDAO.inserir(encaminhamentoExterno);
		
	}

	@Override
	public void alterarEncaminhamento(EncaminhamentoExterno encaminhamentoExterno) {
		encaminhamentoDAO.alterar(encaminhamentoExterno);
		
	}

	@Override
	public List<EncaminhamentoExterno> listarEncaminhamentoFiltro(EncaminhamentoExterno encaminhamentoExterno) {
		
		return encaminhamentoDAO.listarEncaminhamentoFiltro(encaminhamentoExterno);
	}

}
