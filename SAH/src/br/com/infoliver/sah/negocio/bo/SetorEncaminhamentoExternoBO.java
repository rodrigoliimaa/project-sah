package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.SetorEncaminhamentoExterno;

public interface SetorEncaminhamentoExternoBO {

	List<SetorEncaminhamentoExterno> listarSetor();
	void inserirSetorEncaminhamento(SetorEncaminhamentoExterno setorEncaminhamento);
}
