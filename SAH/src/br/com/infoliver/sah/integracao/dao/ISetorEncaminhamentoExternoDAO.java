package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.SetorEncaminhamentoExterno;

public interface ISetorEncaminhamentoExternoDAO {
	List<SetorEncaminhamentoExterno> listarSetor();
	void inserir(SetorEncaminhamentoExterno setor);

}
