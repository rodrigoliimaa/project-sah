package br.com.infoliver.sah.negocio.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.exception.DBException;
import br.com.infoliver.sah.configuracao.exception.TransactionalException;
import br.com.infoliver.sah.configuracao.resource.ResourceConstant;
import br.com.infoliver.sah.configuracao.resource.ResourceUtils;
import br.com.infoliver.sah.configuracao.validacao.ValidadorEntidade;
import br.com.infoliver.sah.integracao.dao.IFornecedorDAO;
import br.com.infoliver.sah.negocio.bo.IFornecedorBO;
import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.entity.Fornecedor.AlterarFornecedor;
import br.com.infoliver.sah.negocio.entity.Fornecedor.InserirFornecedor;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("fornecedorBO")
@TransactionalException
@Transactional(readOnly=true)
public class FornecedorBO implements IFornecedorBO {
	@Autowired private IFornecedorDAO fornecedorDAO;
	@Autowired private PaginacaoVO paginacaoVO;
	@Autowired private RetornoVO retornoVO;
	@Autowired private ValidadorEntidade validadorEntidade;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RetornoVO alterar(Fornecedor fornecedor) {
		validadorEntidade.validar(Fornecedor.class,fornecedor,AlterarFornecedor.class).lancarExcecao();
		
		try {
			fornecedorDAO.alterar(fornecedor);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RetornoVO inserir(Fornecedor fornecedor) {
		validadorEntidade.validar(Fornecedor.class,fornecedor,InserirFornecedor.class).lancarExcecao();
		
		try {
			fornecedorDAO.inserir(fornecedor);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
	}

	@Override
	public PaginacaoVO listarPaginado(PaginacaoVO fornecedor) {
		try {
			
			Integer totalRegistros = fornecedorDAO.totalRegitrosParaPaginacao((Fornecedor) fornecedor.getEntidade());
			List<Fornecedor> lista=fornecedorDAO.listarPaginado(fornecedor);
			paginacaoVO.setEntidade(lista);
			paginacaoVO.setTotalRegistros(totalRegistros);
			return paginacaoVO;
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	@Override
	public List<Fornecedor> listarFornecedores() {
		try {
			List<Fornecedor> lista = fornecedorDAO.listarFornecedores();
			return lista;
		} catch (Exception e) {
			throw new BOException();
		}
		
	}
	
	@Override
	public Fornecedor obter(Fornecedor fornecedor) {
		if (fornecedor == null) {
			throw new BOException("O FORNECEDOR não deveria ser nulo");
		}
		
		if (fornecedor.getSequencial() == null) {
			throw new BOException("O CÓDIGO do FORNECEDOR não deveria ser nulo");
		}
		
		Fornecedor fornecedorRecuperado = null;
		try {
			fornecedorRecuperado = fornecedorDAO.obter(fornecedor);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException("Não foi possível recuperar o fornecedor");
		}
		
		if (fornecedorRecuperado == null) {
			throw new BOException("Nenhum fornecedor encontrado para o código " + fornecedor.getSequencial());
		}
		
		return fornecedorRecuperado;
	}

}
