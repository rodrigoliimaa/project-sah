package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.IRelatorioDAO;
import br.com.infoliver.sah.negocio.bo.IRelatorioBO;
import br.com.infoliver.sah.negocio.entity.Relatorio;

@Service("relatorioBO")
public class RelatorioBO implements IRelatorioBO {
	@Autowired	private IRelatorioDAO relatorioDAO;

	@Override
	public List<Relatorio> listarRelatorioProcedimento() {
		return relatorioDAO.listarRelatorioProcedimento();
	}

	@Override
	public Relatorio consultar(Integer sequencial) {
		return relatorioDAO.consultar(sequencial);
	}
}