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
import br.com.infoliver.sah.integracao.dao.IFeriadoDAO;
import br.com.infoliver.sah.negocio.bo.IFeriadoBO;
import br.com.infoliver.sah.negocio.entity.Feriado;
import br.com.infoliver.sah.negocio.entity.Feriado.AlterarFeriado;
import br.com.infoliver.sah.negocio.entity.Feriado.ExcluirFeriado;
import br.com.infoliver.sah.negocio.entity.Feriado.InserirFeriado;
import br.com.infoliver.sah.negocio.entity.Feriado.ValidarFeriado;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("feriadoBO")
@TransactionalException
@Transactional(readOnly=true)
public class FeriadoBO implements IFeriadoBO {
	
	@Autowired private IFeriadoDAO feriadoDAO;
	@Autowired private PaginacaoVO paginacaoVO;
	@Autowired private RetornoVO retornoVO;
	@Autowired private ValidadorEntidade validadorEntidade;
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO alterar(Feriado feriado) {
		validadorEntidade.validar(Feriado.class, feriado, AlterarFeriado.class, ValidarFeriado.class).lancarExcecao();
		
		if (feriado.getSequencial() == null) {
			throw new BOException("O CODIGO do feriado nao deve ser nulo");
		}
		
		if (feriado.getSequencial() <= 0) {
			throw new BOException("O CODIGO do feriado deve ser maior que 0");
		}
		
		if (feriado.getDataFim() == null) {
			feriado.setDataFim(feriado.getDataInicio());
		}
		
		if (feriado.getDataInicio().after(feriado.getDataFim())) {
			throw new BOException("A data do fim do feriado precisa ser depois da data do inicio");
		}
		
		if (feriado.getSempreNaMesmaData().equals("S") == false
				&& feriado.getSempreNaMesmaData().equals("N") == false) {
			throw new BOException("SEMPRE NA MESMA DATA: valor incorreto - deve ser S (verdadeiro) ou N (falso)");
		}
		
		try {
			feriadoDAO.alterar(feriado);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException(e);
		}
		
		return retornoVO.retornar(null, ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO excluirFeriado(Feriado feriado) {
		validadorEntidade.validar(Feriado.class, feriado, ExcluirFeriado.class).lancarExcecao();
		try {
			feriadoDAO.excluir(feriado);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException(e);
		}
		
		return retornoVO.retornar(null, ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroExcluidoComSucesso));
	}

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO inserir(Feriado feriado) {
		validadorEntidade.validar(Feriado.class, feriado, InserirFeriado.class, ValidarFeriado.class).lancarExcecao();
		
		if (feriado.getDataFim() == null) {
			feriado.setDataFim(feriado.getDataInicio());
		}
		
		if (feriado.getDataInicio().after(feriado.getDataFim())) {
			throw new BOException("A data do fim do feriado precisa ser depois da data do inicio");
		}
		
		if (feriado.getSempreNaMesmaData().equals("S") == false
				&& feriado.getSempreNaMesmaData().equals("N") == false) {
			throw new BOException("SEMPRE NA MESMA DATA: valor incorreto - deve ser S (verdadeiro) ou N (falso)");
		}
		
		try {
			feriadoDAO.inserir(feriado);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return retornoVO.retornar(null, ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
	}

	@Override
	public List<Feriado> listar() {
		return feriadoDAO.listar();
	}

}
