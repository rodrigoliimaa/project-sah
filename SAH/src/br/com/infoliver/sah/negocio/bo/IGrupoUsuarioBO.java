package br.com.infoliver.sah.negocio.bo;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.negocio.entity.GrupoUsuario;

public interface IGrupoUsuarioBO {
	void inserir(GrupoUsuario grupoUsuario)throws BOException;

	void excluir(GrupoUsuario grupoUsuario)throws BOException;
}