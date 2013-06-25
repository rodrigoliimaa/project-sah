package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.TransactionalException;
import br.com.infoliver.sah.integracao.dao.IEquipamentoDAO;
import br.com.infoliver.sah.integracao.dao.IFornecedorDAO;
import br.com.infoliver.sah.negocio.bo.IEquipamentoBO;
import br.com.infoliver.sah.negocio.entity.Equipamento;

@Service("equipamentoBO")
@TransactionalException
@Transactional(readOnly=true)
public class EquipamentoBO implements IEquipamentoBO {
	@Autowired private IEquipamentoDAO equipamentoDAO;
	
	@Override
	public List<Equipamento> listar() {
		return equipamentoDAO.listar();
	}
	
	

}
