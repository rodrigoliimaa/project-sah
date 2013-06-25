package br.com.infoliver.sah.negocio.bo;

import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface ILogAcessoBO {
	
	PaginacaoVO listarPaginado(PaginacaoVO logAcesso);
}