package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.SetorEncaminhamentoExterno;

public interface ISetorEncaminhamentoExternoBO {
	List<SetorEncaminhamentoExterno> listarSetor();
	void inserirSetor(SetorEncaminhamentoExterno encaminhamentoExterno);

}
