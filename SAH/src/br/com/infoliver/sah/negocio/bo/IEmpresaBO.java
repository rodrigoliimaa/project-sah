package br.com.infoliver.sah.negocio.bo;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.negocio.entity.Empresa;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IEmpresaBO {
	Empresa consultar();
	
	RetornoVO inserir(Empresa empresa)throws BOException;
	
	RetornoVO alterar(Empresa empresa)throws BOException;
}