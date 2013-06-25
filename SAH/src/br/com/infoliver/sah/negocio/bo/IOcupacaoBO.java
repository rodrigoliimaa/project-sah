package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Ocupacao;

public interface IOcupacaoBO {
	List<Ocupacao> listar();

	List<Ocupacao> pesquisarOcupacaoPorDescricao(String descricao);
}