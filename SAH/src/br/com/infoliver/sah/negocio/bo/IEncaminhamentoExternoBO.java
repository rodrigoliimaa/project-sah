package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.EncaminhamentoExterno;

public interface IEncaminhamentoExternoBO {

	List<EncaminhamentoExterno> listarEncaminhamento();
	List<EncaminhamentoExterno> listarEncaminhamentoFiltro(EncaminhamentoExterno encaminhamentoExterno);
	void inserirEncaminhamento(EncaminhamentoExterno encaminhamentoExterno);
	void alterarEncaminhamento(EncaminhamentoExterno encaminhamentoExterno);
}
