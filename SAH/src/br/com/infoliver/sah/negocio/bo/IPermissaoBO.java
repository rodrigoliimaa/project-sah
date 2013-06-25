package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Permissao;

public interface IPermissaoBO {

	List<Permissao> listarPermissaoDoGrupo(Integer sequencialGrupo);

	List<Permissao> listarPermissaoDiferenteDoGrupo(Integer sequencialGrupo);
}