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
import br.com.infoliver.sah.configuracao.util.MD5;
import br.com.infoliver.sah.configuracao.validacao.ValidadorEntidade;
import br.com.infoliver.sah.integracao.dao.IArquivoDAO;
import br.com.infoliver.sah.negocio.bo.IArquivoBO;
import br.com.infoliver.sah.negocio.entity.Arquivo;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("arquivoBO")
@TransactionalException
@Transactional(readOnly=true)
public class ArquivoBO implements IArquivoBO{
	@Autowired private IArquivoDAO arquivoDAO;

	@Autowired private ValidadorEntidade validadorEntidade;

	@Autowired private RetornoVO retornoVO;

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void inserir(Arquivo arquivo)throws BOException{
		validadorEntidade.validar(Arquivo.class,arquivo,Arquivo.InserirArquivo.class).lancarExcecao();
		try {
			arquivoDAO.inserir(arquivo);			
		} catch (Exception e) {
			throw new DBException(e,arquivo.getNome());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RetornoVO excluir(Arquivo arquivo)throws BOException{
		validadorEntidade.validar(Arquivo.class,arquivo,Arquivo.ExcluirArquivo.class).lancarExcecao();
		List<Arquivo> lista=arquivoDAO.excluir(arquivo);
		return retornoVO.retornar(lista,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroExcluidoComSucesso));
	}

	@Override
	public List<Arquivo> listar(Integer sequencialPaciente){
		return arquivoDAO.listar(sequencialPaciente);
	}

	@Override
	public Arquivo consultarImagem(Integer sequencial) {
		Arquivo arquivo=arquivoDAO.consultarImagem(sequencial);
		String hashImagem= MD5.getInstance().hashData(arquivo.getImagemArquivo());
		if(!arquivo.getCodigoHash().equals(hashImagem))
			throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemErroArquivoAdulterado));

		return arquivo;
	}
}