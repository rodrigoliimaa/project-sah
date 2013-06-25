package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.negocio.entity.Arquivo;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IArquivoBO {
	void inserir(Arquivo arquivo)throws BOException;
	
	RetornoVO excluir(Arquivo arquivo)throws BOException;
	
	List<Arquivo> listar(Integer sequencialPaciente);
	
	Arquivo consultarImagem(Integer sequencial);
}