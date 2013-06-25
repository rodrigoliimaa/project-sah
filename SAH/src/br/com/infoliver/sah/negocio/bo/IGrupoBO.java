package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.negocio.entity.Grupo;

public interface IGrupoBO {
	void inserir(Grupo grupo)throws BOException;

	void alterar(Grupo grupo)throws BOException;

	void excluir(Grupo grupo)throws BOException;

	List<Grupo> listar();
}