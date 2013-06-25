package br.com.infoliver.sah.negocio.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.resource.ResourceConstant;
import br.com.infoliver.sah.configuracao.resource.ResourceUtils;
import br.com.infoliver.sah.configuracao.validacao.ValidadorEntidade;
import br.com.infoliver.sah.integracao.dao.IEmpresaDAO;
import br.com.infoliver.sah.negocio.bo.IEmpresaBO;
import br.com.infoliver.sah.negocio.entity.Empresa;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("empresaBO")
public class EmpresaBO implements IEmpresaBO{
	@Autowired private IEmpresaDAO empresaDAO;

	@Autowired private ValidadorEntidade validadorEntidade;

	@Autowired private RetornoVO retornoVO;
	
	@Override
	public Empresa consultar() {
		return empresaDAO.consultar();
	}

	@Override
	public RetornoVO inserir(Empresa empresa) throws BOException {
		validadorEntidade.validar(Empresa.class,empresa,Empresa.InserirEmpresa.class).lancarExcecao();
		empresaDAO.inserir(empresa);
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
	}

	@Override
	public RetornoVO alterar(Empresa empresa) throws BOException {
		validadorEntidade.validar(Empresa.class,empresa,Empresa.AlterarEmpresa.class).lancarExcecao();
		empresaDAO.alterar(empresa);
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}
}