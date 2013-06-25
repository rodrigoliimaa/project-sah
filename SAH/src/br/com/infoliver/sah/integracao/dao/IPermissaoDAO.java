package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Permissao;
import br.com.infoliver.sah.negocio.entity.Usuario;

public interface IPermissaoDAO {

	List<Object> listarPermissaoUsuario(Usuario usuario);
	
	List<Permissao> listarPermissaoDoGrupo(Integer sequencialGrupo);

	List<Permissao> listarPermissaoDiferenteDoGrupo(Integer sequencialGrupo);
}