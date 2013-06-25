package br.com.infoliver.sah.negocio.bo;

import br.com.infoliver.sah.negocio.entity.Licenca;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface ILicencaBO {

	RetornoVO alterar(Licenca licenca);
	
	RetornoVO excluir(Licenca licenca);
	
	RetornoVO inserir(Licenca licenca);
	
	PaginacaoVO listarPaginado(PaginacaoVO licenca);

}
