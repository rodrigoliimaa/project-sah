package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Ocupacao;

public interface IOcupacaoDAO {
	List<Ocupacao> listar();

	List<Ocupacao> pesquisarPorDescricao(String descricao);

	Ocupacao consultar(Integer sequencial);
}