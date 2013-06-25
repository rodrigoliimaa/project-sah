package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.exception.TransactionalException;
import br.com.infoliver.sah.negocio.bo.IEntrevistaBO;
import br.com.infoliver.sah.negocio.entity.Entrevista;
import br.com.infoliver.sah.negocio.entity.PreAtendimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("entrevistaBO")
@TransactionalException
@Transactional(readOnly=true)
public class EntrevistaBO implements IEntrevistaBO {

	@Override
	public RetornoVO inserirEntrevista(Entrevista entrevista)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Entrevista entrevista) throws BOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Entrevista entrevista) throws BOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer totalRegitrosParaPaginacao(PaginacaoVO entrevista) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entrevista> listarPaginado(PaginacaoVO entrevista) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginacaoVO listar(PaginacaoVO entrevista) {
		// TODO Auto-generated method stub
		return null;
	}

}
