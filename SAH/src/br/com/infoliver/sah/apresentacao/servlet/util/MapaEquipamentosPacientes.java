package br.com.infoliver.sah.apresentacao.servlet.util;

import java.util.List;

import flex.messaging.io.ArrayList;

import br.com.infoliver.sah.negocio.entity.Movimentacao;

public class MapaEquipamentosPacientes {

	@SuppressWarnings("unchecked")
	private List<KeyValue> listaApacBpiPacientes = new ArrayList();

	public MapaEquipamentosPacientes() {}
	
	public List<KeyValue> getListaApacBpiPacientes() {
		return listaApacBpiPacientes;
	}

	public void adicionarMovimentacao(Movimentacao movimentacao){
		KeyValue keyValueAdd = new KeyValue();
		keyValueAdd.setApacBpi(movimentacao.getApacBpi());

		if (!contemChave(movimentacao.getApacBpi())) {
			keyValueAdd.setObservacao(movimentacao.getObservacao());
			keyValueAdd.adicionarPaciente(movimentacao.getPaciente());
			
			listaApacBpiPacientes.add(keyValueAdd);
		} else {
			for (KeyValue keyValue : listaApacBpiPacientes) {
				if (keyValue.equals(keyValueAdd)) {
					keyValue.adicionarPaciente(movimentacao.getPaciente());
				}
			}
		}
	}
	
	private boolean contemChave(String key) {
		for (KeyValue keyValue : listaApacBpiPacientes) {
			if (key.equals(keyValue.getApacBpi())) {
				return true;
			}
		}
		
		return false;
	}
	
}
