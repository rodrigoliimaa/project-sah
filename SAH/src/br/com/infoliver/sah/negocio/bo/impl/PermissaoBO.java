package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.integracao.dao.IPermissaoDAO;
import br.com.infoliver.sah.negocio.bo.IPermissaoBO;
import br.com.infoliver.sah.negocio.entity.Permissao;

@Service("permissaoBO")
public class PermissaoBO implements IPermissaoBO {

	@Autowired
	private IPermissaoDAO permissaoDAO;

	@Override
	public List<Permissao> listarPermissaoDoGrupo(Integer sequencialGrupo) {
		return permissaoDAO.listarPermissaoDoGrupo(sequencialGrupo);
	}

	@Override
	public List<Permissao> listarPermissaoDiferenteDoGrupo(Integer sequencialGrupo) {
		return permissaoDAO.listarPermissaoDiferenteDoGrupo(sequencialGrupo);
	}
}