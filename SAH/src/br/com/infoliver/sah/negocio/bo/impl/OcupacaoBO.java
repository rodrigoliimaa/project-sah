package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.integracao.dao.IOcupacaoDAO;
import br.com.infoliver.sah.negocio.bo.IOcupacaoBO;
import br.com.infoliver.sah.negocio.entity.Ocupacao;

@Service("ocupacaoBO")
public class OcupacaoBO implements IOcupacaoBO{

	@Autowired private IOcupacaoDAO ocupacaoDAO;
	
	@Override
	public List<Ocupacao> listar() {
		return ocupacaoDAO.listar();
	}

	@Override
	public List<Ocupacao> pesquisarOcupacaoPorDescricao(String descricao) {
		if (descricao == null) {
			throw new BOException("O campo DESCRIÇÃO DA OCUPAÇÃO é obrigatório para a pesquisa");
		}
		
		descricao = descricao.trim();
		if (descricao.equals("")) {
			throw new BOException("O campo DESCRIÇÃO não pode ser vazio para pesquisa");
		}
		
		return ocupacaoDAO.pesquisarPorDescricao(descricao);
	}
}