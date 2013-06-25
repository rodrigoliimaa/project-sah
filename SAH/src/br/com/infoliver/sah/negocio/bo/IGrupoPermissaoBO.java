package br.com.infoliver.sah.negocio.bo;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.negocio.entity.GrupoPermissao;

public interface IGrupoPermissaoBO {
	void inserir(GrupoPermissao grupoPermissao)throws BOException;

	void excluir(GrupoPermissao grupoPermissao)throws BOException;
}