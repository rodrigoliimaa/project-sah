package br.com.infoliver.sah.negocio.bo.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
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
import br.com.infoliver.sah.integracao.dao.IMedicoDAO;
import br.com.infoliver.sah.integracao.dao.IMovimentacaoDAO;
import br.com.infoliver.sah.integracao.dao.IOcupacaoDAO;
import br.com.infoliver.sah.integracao.dao.IPacienteDAO;
import br.com.infoliver.sah.integracao.dao.ISigtapDAO;
import br.com.infoliver.sah.negocio.bo.IMovimentacaoBO;
import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.entity.Movimentacao.AlterarMovimentacao;
import br.com.infoliver.sah.negocio.entity.Movimentacao.InserirMovimentacao;
import br.com.infoliver.sah.negocio.entity.MovimentacaoCID;
import br.com.infoliver.sah.negocio.entity.MovimentacaoProcedimento;
import br.com.infoliver.sah.negocio.entity.SigtapCID;
import br.com.infoliver.sah.negocio.entity.SigtapProcedimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("movimentacaoBO")
@TransactionalException
@Transactional(readOnly=true)
public class MovimentacaoBO implements IMovimentacaoBO {
	@Autowired private IMovimentacaoDAO movimentacaoDAO;
	@Autowired private PaginacaoVO paginacaoVO;
	@Autowired private RetornoVO retornoVO;
	@Autowired private ValidadorEntidade validadorEntidade;
	
	@Autowired private IFornecedorDAO fornecedorDAO;
	@Autowired private IMedicoDAO medicoDAO;
	@Autowired private IOcupacaoDAO ocupacaoDAO;
	@Autowired private IPacienteDAO pacienteDAO;
	@Autowired private ISigtapDAO sigtapDAO;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO alterar(Movimentacao movimentacao) {
		validadorEntidade.validar(Movimentacao.class,movimentacao,AlterarMovimentacao.class).lancarExcecao();
		

		verificarDatas(movimentacao);
		verificarNumeroNota(movimentacao);
		verificarValorMovimentacao(movimentacao);
		
		try {
			movimentacaoDAO.alterar(movimentacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}
	
	private void verificarDatas(Movimentacao movimentacao) {
		if (movimentacao.getPrograma().isOrteseProtese()) {
			verificarAntecedencia(movimentacao.getDataSolicitacao(), movimentacao.getDataEntrada(), "SOLICITAÇÃO", "ENTRADA");
			verificarAntecedencia(movimentacao.getDataEntrada(), movimentacao.getDataEncaminhamento(), "ENTRADA", "ENCAMINHAMENTO");
			verificarAntecedencia(movimentacao.getDataEncaminhamento(), movimentacao.getDataEntrega(), "ENCAMINHAMENTO", "ENTREGA");
		} else {
			verificarAntecedencia(movimentacao.getDataSolicitacao(), movimentacao.getDataEntrada(), "SOLICITAÇÃO", "ENTRADA");
			verificarAntecedencia(movimentacao.getDataEntrada(), movimentacao.getDataEntrega(), "ENTRADA", "ENTREGA");
		}
		
		verificarAntecedencia(movimentacao.getDataSolicitacao(), movimentacao.getDataAutorizacao(), "SOLICITAÇÃO", "AUTORIZAÇÃO");
	}

	private void verificarAntecedencia(Date anterior, Date posterior, String descricaoAnterior, String descricaoPosterior) {
		if (posterior != null && anterior == null) {
			throw new BOException("A data de " + descricaoAnterior + " é necessária para a data de " + descricaoPosterior);
		}
		
		anterior = zerarHorasMinutosSegundos(anterior);
		posterior = zerarHorasMinutosSegundos(posterior);
		
		if (posterior != null && posterior.before(anterior)) {
			throw new BOException("A data de " + descricaoPosterior + " deve ser maior ou igual a data de " + descricaoAnterior);
		}
	}

	private Date zerarHorasMinutosSegundos(Date data) {
		if (data != null) {
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(data);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		}
		return null;
	}
	
	private void verificarNumeroNota(Movimentacao movimentacao) {
		final Movimentacao movimentacaoRecuperada = movimentacaoDAO.consultarPorNumeroNota(movimentacao.getNumeroNota());
		if (movimentacaoRecuperada != null &&
				movimentacaoRecuperada.getSequencial().intValue() != movimentacao.getSequencial().intValue()) {
			throw new BOException("O número da nota da MOVIMENTAÇÃO deveria ser único");
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO inserir(Movimentacao movimentacao) {
		try {
			validadorEntidade.validar(Movimentacao.class, movimentacao,	InserirMovimentacao.class).lancarExcecao();

			verificarQuantidades(movimentacao);

			if (movimentacao.getSigtap_dt_competencia() == null
					|| movimentacao.getSigtap_dt_competencia().trim()
							.equals("")) {
				movimentacao.setSigtap_dt_competencia(sigtapDAO
						.consultarDtCompetencia());
			}

			try {
				movimentacaoDAO.inserir(movimentacao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DBException(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException(e.getMessage());
		}
		
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
	}
	
	private void verificarQuantidades(Movimentacao movimentacao) {
		verificarQuantidades(movimentacao.getCidCausasAssociadas());
		verificarQuantidades(movimentacao.getCidPrincipal());
		verificarQuantidades(movimentacao.getCidSecundario());
	}
	
	private void verificarQuantidades(MovimentacaoCID cid) {
		if (cid != null) {
			List<MovimentacaoProcedimento> procedimentos = cid.getProcedimentos();
			
			if (procedimentos == null || procedimentos.size() == 0) {
				throw new BOException("Não há procedimentos selecionados para o CID " + cid.getSigtap_co_cid());
			}
			
			for (MovimentacaoProcedimento procedimento : procedimentos) {
				if (procedimento.getQuantidade() == 0) {
					throw new BOException("A quantidade atribuida é menor que 1 para o PROCEDIMENTO " +
							procedimento.getSigtap_co_procedimento() + " - " + procedimento.getSigtap_no_procedimento());
				}
				
				if (procedimento.getQuantidade() > procedimento.getSigtap_qt_maxima_execucao()) {
					throw new BOException("A quantidade atribuida é maior que a quantidade QUANTIDADE MÁXIMA DE EXECUÇÃO para o PROCEDIMENTO " +
							procedimento.getSigtap_co_procedimento() + " - " + procedimento.getSigtap_no_procedimento());
				}
			}
		}
	}

	private void verificarValorMovimentacao(Movimentacao movimentacao) {
		Movimentacao movimentacaoRecuperada = movimentacaoDAO.consultar(movimentacao.getSequencial());
		
		final Fornecedor fornecedorAnterior = movimentacaoRecuperada.getFornecedor();
		final Fornecedor fornecedorNovo = movimentacao.getFornecedor();
		
		if (fornecedorAnterior != null && fornecedorAnterior.getSequencial() != null &&
				fornecedorNovo != null && fornecedorNovo.getSequencial() != null &&
				fornecedorAnterior.getSequencial().intValue() == fornecedorNovo.getSequencial().intValue()) {
			return ;
		}
		
		if (fornecedorNovo != null && fornecedorNovo.getSequencial() != null && fornecedorNovo.getSequencial() > 0) {
			BigDecimal balanco = fornecedorNovo.getValorVendasDisponivel().subtract(new BigDecimal(movimentacao.getValorTotal()));
			if (balanco.signum() < 0) {
				throw new BOException("Valor da MOVIMENTAÇÃO é maior que o valor de vendas disponível para o FORNECEDOR");
			}
		}
	}
	
	@Override
	public PaginacaoVO listarPaginado(PaginacaoVO movimentacao) {
		Movimentacao movimento = new Movimentacao();
		movimento = (Movimentacao) movimentacao.getEntidade();
		
	//	verificarApacBpi(movimento);
		
		try {
			Integer totalRegistros = movimentacaoDAO.totalRegitrosParaPaginacao((Movimentacao) movimentacao.getEntidade());
			List<Movimentacao> lista=movimentacaoDAO.listarPaginado(movimentacao);
			
			for (int i = 0; i < lista.size(); i++) {
				final Medico medico = medicoDAO.consultarPorSequencial(lista.get(i).getMedico().getSequencial());
				medico.setOcupacao(
						ocupacaoDAO.consultar(medico.getOcupacao().getSequencial())
				);
				lista.get(i).setMedico(medico);
				
				lista.get(i).setPaciente(
						pacienteDAO.consultar(lista.get(i).getPaciente().getSequencial())
				);
				
				if (lista.get(i).getFornecedor() != null &&
						lista.get(i).getFornecedor().getSequencial() != null &&
						lista.get(i).getFornecedor().getSequencial() > 0) {
					lista.get(i).setFornecedor(
							fornecedorDAO.consultar(lista.get(i).getFornecedor().getSequencial())
					);
				}
				
//				lista.get(i).setCids(
//						movimentacaoDAO.consultarCids(lista.get(i).getSequencial())
//				);
				
//				lista.get(i).setProcedimentos(
//						movimentacaoDAO.consultarProcedimentos(lista.get(i).getSequencial())
//				);
				lista.get(i).getCidPrincipal().setProcedimentos(
						movimentacaoDAO.consultarProcedimentos(lista.get(i).getSequencial(), lista.get(i).getCidPrincipal().getSigtap_co_cid())
				);
				final SigtapCID sigtapCidPrincipal = sigtapDAO.consultarCid(lista.get(i).getCidPrincipal().getSigtap_co_cid());
				lista.get(i).getCidPrincipal().setSigtap_no_cid(sigtapCidPrincipal.getNo_cid());
				for (MovimentacaoProcedimento procedimento : lista.get(i).getCidPrincipal().getProcedimentos()) {
					final SigtapProcedimento sigtapProcedimento = sigtapDAO.consultarProcedimento(procedimento.getSigtap_co_procedimento());
					procedimento.setSigtap_no_procedimento(sigtapProcedimento.getNo_procedimento());
				}
				
				if (lista.get(i).getCidSecundario() != null &&
						lista.get(i).getCidSecundario().getSigtap_co_cid() != null &&
						lista.get(i).getCidSecundario().getSigtap_co_cid().equals("") == false) {
					lista.get(i).getCidSecundario().setProcedimentos(
						movimentacaoDAO.consultarProcedimentos(lista.get(i).getSequencial(), lista.get(i).getCidSecundario().getSigtap_co_cid())
					);
					final SigtapCID sigtapCidSecundario = sigtapDAO.consultarCid(lista.get(i).getCidSecundario().getSigtap_co_cid());
					lista.get(i).getCidSecundario().setSigtap_no_cid(sigtapCidSecundario.getNo_cid());
					for (MovimentacaoProcedimento procedimento : lista.get(i).getCidSecundario().getProcedimentos()) {
						final SigtapProcedimento sigtapProcedimento = sigtapDAO.consultarProcedimento(procedimento.getSigtap_co_procedimento());
						procedimento.setSigtap_no_procedimento(sigtapProcedimento.getNo_procedimento());
					}
				}
				
				if (lista.get(i).getCidCausasAssociadas() != null &&
						lista.get(i).getCidCausasAssociadas().getSigtap_co_cid() != null &&
						lista.get(i).getCidCausasAssociadas().getSigtap_co_cid().equals("") == false){
					lista.get(i).getCidCausasAssociadas().setProcedimentos(
							movimentacaoDAO.consultarProcedimentos(lista.get(i).getSequencial(), lista.get(i).getCidCausasAssociadas().getSigtap_co_cid())
					);
					final SigtapCID sigtapCidCausasAssociadas = sigtapDAO.consultarCid(lista.get(i).getCidCausasAssociadas().getSigtap_co_cid());
					lista.get(i).getCidCausasAssociadas().setSigtap_no_cid(sigtapCidCausasAssociadas.getNo_cid());
					for (MovimentacaoProcedimento procedimento : lista.get(i).getCidCausasAssociadas().getProcedimentos()) {
						final SigtapProcedimento sigtapProcedimento = sigtapDAO.consultarProcedimento(procedimento.getSigtap_co_procedimento());
						procedimento.setSigtap_no_procedimento(sigtapProcedimento.getNo_procedimento());
					}
				}
			}
			
			paginacaoVO.setEntidade(lista);
			paginacaoVO.setTotalRegistros(totalRegistros);
			return paginacaoVO;
		} catch (Exception e) {
			throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemAvisoCampoObrigatorio));
		}
	}
	
	@Override
	public PaginacaoVO listarPaginandoAuditivo(String tipoPesquisa) {
		
		try {
			Integer totalRegistros = movimentacaoDAO.totalRegitrosParaPaginacaoAuditivo(tipoPesquisa);
			List<Movimentacao> lista = movimentacaoDAO.listarPaginandoAuditivo(tipoPesquisa);
			
			for (int i = 0; i < lista.size(); i++) {
				final Medico medico = medicoDAO.consultarPorSequencial(lista.get(i).getMedico().getSequencial());
				medico.setOcupacao(
						ocupacaoDAO.consultar(medico.getOcupacao().getSequencial())
				);
				lista.get(i).setMedico(medico);
				
				lista.get(i).setPaciente(
						pacienteDAO.consultar(lista.get(i).getPaciente().getSequencial())
				);
				
				if (lista.get(i).getFornecedor() != null &&
						lista.get(i).getFornecedor().getSequencial() != null &&
						lista.get(i).getFornecedor().getSequencial() > 0) {
					lista.get(i).setFornecedor(
							fornecedorDAO.consultar(lista.get(i).getFornecedor().getSequencial())
					);
				}
				
				lista.get(i).getCidPrincipal().setProcedimentos(
						movimentacaoDAO.consultarProcedimentos(lista.get(i).getSequencial(), lista.get(i).getCidPrincipal().getSigtap_co_cid())
				);
				final SigtapCID sigtapCidPrincipal = sigtapDAO.consultarCid(lista.get(i).getCidPrincipal().getSigtap_co_cid());
				lista.get(i).getCidPrincipal().setSigtap_no_cid(sigtapCidPrincipal.getNo_cid());
				for (MovimentacaoProcedimento procedimento : lista.get(i).getCidPrincipal().getProcedimentos()) {
					final SigtapProcedimento sigtapProcedimento = sigtapDAO.consultarProcedimento(procedimento.getSigtap_co_procedimento());
					procedimento.setSigtap_no_procedimento(sigtapProcedimento.getNo_procedimento());
				}
				
				if (lista.get(i).getCidSecundario() != null &&
						lista.get(i).getCidSecundario().getSigtap_co_cid() != null &&
						lista.get(i).getCidSecundario().getSigtap_co_cid().equals("") == false) {
					lista.get(i).getCidSecundario().setProcedimentos(
						movimentacaoDAO.consultarProcedimentos(lista.get(i).getSequencial(), lista.get(i).getCidSecundario().getSigtap_co_cid())
					);
					final SigtapCID sigtapCidSecundario = sigtapDAO.consultarCid(lista.get(i).getCidSecundario().getSigtap_co_cid());
					lista.get(i).getCidSecundario().setSigtap_no_cid(sigtapCidSecundario.getNo_cid());
					for (MovimentacaoProcedimento procedimento : lista.get(i).getCidSecundario().getProcedimentos()) {
						final SigtapProcedimento sigtapProcedimento = sigtapDAO.consultarProcedimento(procedimento.getSigtap_co_procedimento());
						procedimento.setSigtap_no_procedimento(sigtapProcedimento.getNo_procedimento());
					}
				}
				
				if (lista.get(i).getCidCausasAssociadas() != null &&
						lista.get(i).getCidCausasAssociadas().getSigtap_co_cid() != null &&
						lista.get(i).getCidCausasAssociadas().getSigtap_co_cid().equals("") == false){
					lista.get(i).getCidCausasAssociadas().setProcedimentos(
							movimentacaoDAO.consultarProcedimentos(lista.get(i).getSequencial(), lista.get(i).getCidCausasAssociadas().getSigtap_co_cid())
					);
					final SigtapCID sigtapCidCausasAssociadas = sigtapDAO.consultarCid(lista.get(i).getCidCausasAssociadas().getSigtap_co_cid());
					lista.get(i).getCidCausasAssociadas().setSigtap_no_cid(sigtapCidCausasAssociadas.getNo_cid());
					for (MovimentacaoProcedimento procedimento : lista.get(i).getCidCausasAssociadas().getProcedimentos()) {
						final SigtapProcedimento sigtapProcedimento = sigtapDAO.consultarProcedimento(procedimento.getSigtap_co_procedimento());
						procedimento.setSigtap_no_procedimento(sigtapProcedimento.getNo_procedimento());
					}
				}
			}
			
			paginacaoVO.setEntidade(lista);
			paginacaoVO.setTotalRegistros(totalRegistros);
			return paginacaoVO;
		} catch (Exception e) {
			throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemAvisoCampoObrigatorio));
		}
	}	
	
	@Override
	public PaginacaoVO listarPaginadoParaRelatorio(PaginacaoVO movimentacao) {
		try {
			Integer totalRegistros = movimentacaoDAO.totalRegitrosParaPaginacao((Movimentacao) movimentacao.getEntidade());
			
			List<Movimentacao> lista = movimentacaoDAO.listarPaginandoParaRelatorio(movimentacao);
			
			paginacaoVO.setEntidade(lista);
			paginacaoVO.setTotalRegistros(totalRegistros);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paginacaoVO;
	}

	@Override
	public List<Movimentacao> listarRelatorioMapa(Movimentacao movimentacao) {
		try {
		
			return movimentacaoDAO.listarRelatorioMapa(movimentacao);
			
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	@Override
	public List<Movimentacao> listarRelatorioEncaminhamento(
			Movimentacao moviemntacao) {
		try {
			return movimentacaoDAO.listarRelatorioEncaminhamento(moviemntacao);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	@Override
	public List<Movimentacao> listarRelatorioTermoCompromisso(
			Movimentacao movimentacao) {
		try {
			return movimentacaoDAO.listarRelatorioTermoCompromisso(movimentacao);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	@Override
	public List<Movimentacao> listarRelatorioMapaBpi(Movimentacao movimentacao) {
		try {
			return movimentacaoDAO.listarRelatorioMapaBpi(movimentacao);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	@Override
	public List<Movimentacao> listarRelatorioEquipamentos(
			Movimentacao movimentacao) {
		try {
			return movimentacaoDAO.listarRelatorioEquipamentos(movimentacao);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	@Override
	public List<Movimentacao> listarRelatorioEquipamentosSintetico(
			Movimentacao movimentacao) {
		try {
			return movimentacaoDAO.listarRelatorioEquipamentosSintetico(movimentacao);
		} catch (Exception e) {
			throw new BOException(e);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluirMovimentacao(Movimentacao movimentacao) {
		try {
			movimentacaoDAO.excluirMovimentacao(movimentacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
