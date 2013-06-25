package br.com.infoliver.sah.negocio.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.integracao.dao.IGrupoPermissaoDAO;
import br.com.infoliver.sah.negocio.bo.IGrupoPermissaoBO;
import br.com.infoliver.sah.negocio.entity.GrupoPermissao;

@Service("grupoPermissaoBO")
public class GrupoPermissaoBO implements IGrupoPermissaoBO {

	@Autowired
	private IGrupoPermissaoDAO grupoPermissaoDAO;
	
	@Override
	public void inserir(GrupoPermissao grupoPermissao) throws BOException {
		grupoPermissaoDAO.inserir(grupoPermissao);
	}

	@Override
	public void excluir(GrupoPermissao grupoPermissao) throws BOException {
		grupoPermissaoDAO.excluir(grupoPermissao);
	}
}