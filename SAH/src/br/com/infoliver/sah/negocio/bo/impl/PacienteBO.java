package br.com.infoliver.sah.negocio.bo.impl;

import java.util.Calendar;
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
import br.com.infoliver.sah.configuracao.util.CNS;
import br.com.infoliver.sah.configuracao.util.MD5;
import br.com.infoliver.sah.configuracao.validacao.ValidadorEntidade;
import br.com.infoliver.sah.integracao.dao.IPacienteDAO;
import br.com.infoliver.sah.negocio.bo.IArquivoBO;
import br.com.infoliver.sah.negocio.bo.IPacienteBO;
import br.com.infoliver.sah.negocio.entity.Arquivo;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("pacienteBO")
@TransactionalException
@Transactional(readOnly=true)
public class PacienteBO implements IPacienteBO{
	@Autowired private IPacienteDAO pacienteDAO;
	@Autowired private IArquivoBO arquivoBO;
	@Autowired private ValidadorEntidade validadorEntidade;
	@Autowired private PaginacaoVO paginacaoVO;
	@Autowired private RetornoVO retornoVO;
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO inserir(Paciente paciente)throws BOException{
		if(!CNS.validar(paciente.getCns())) {
			throw new BOException("CNS Invalido!");
		}
		validadorEntidade.validar(Paciente.class,paciente,Paciente.InserirPaciente.class).lancarExcecao();
		//-------------------------------------------------------------------------------------------------
		int idade=Calendar.getInstance().getTime().getYear() - paciente.getDataNascimento().getYear();
		if(idade<18)
			validadorEntidade.validar(Paciente.class,paciente,Paciente.ValidarPacienteMenor.class).lancarExcecao();		
		else
			validadorEntidade.validar(Paciente.class,paciente,Paciente.ValidarPaciente.class).lancarExcecao();
		//-------------------------------------------------------------------------------------------------
		try {
			Integer sequencial=pacienteDAO.inserir(paciente);
			paciente.setSequencial(sequencial);
		} catch (Exception e) {
			throw new DBException(e);
		}
		
		//------------------------------------------------
		inserirArquivo(paciente);
		//------------------------------------------------
		return retornoVO.retornar(paciente,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
	}
	
	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@SuppressWarnings("deprecation")
	public RetornoVO alterar(Paciente paciente)throws BOException{
		validadorEntidade.validar(Paciente.class,paciente,Paciente.AlterarPaciente.class).lancarExcecao();
		//-------------------------------------------------------------------------------------------------
		int idade=Calendar.getInstance().getTime().getYear() - paciente.getDataNascimento().getYear();
		if(idade<18)
			validadorEntidade.validar(Paciente.class,paciente,Paciente.ValidarPacienteMenor.class).lancarExcecao();		
		else
			validadorEntidade.validar(Paciente.class,paciente,Paciente.ValidarPaciente.class).lancarExcecao();
		//-------------------------------------------------------------------------------------------------
		
		if(!CNS.validar(paciente.getCns())) {
			throw new BOException("CNS Invalido!");
		}
		
		try {
			pacienteDAO.alterar(paciente);
		} catch (Exception e) {
			throw new DBException(e);
		}
		//------------------------------------------------
		inserirArquivo(paciente);
		//------------------------------------------------
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
	}

	private void inserirArquivo(Paciente paciente) throws BOException{
		Integer sequencialPaciente=paciente.getSequencial();
		Integer sequencialUsuario=paciente.getUsuarioCadastro().getSequencial();
		for (Arquivo obj: paciente.getArquivos()) {
			Arquivo arq=new Arquivo();
			arq.setNome(obj.getNome());
			arq.setTamanho(obj.getTamanho());
			arq.setImagemArquivo(obj.getImagemArquivo());
			arq.setCodigoHash(MD5.getInstance().hashData(obj.getImagemArquivo()));
			arq.setPaciente(new Paciente(sequencialPaciente));
			arq.setUsuario(new Usuario(sequencialUsuario));
			arquivoBO.inserir(arq);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RetornoVO excluir(Paciente paciente)throws BOException{
		validadorEntidade.validar(Paciente.class,paciente,Paciente.ExcluirPaciente.class).lancarExcecao();
		pacienteDAO.excluir(paciente);
		return retornoVO.retornar(null,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroExcluidoComSucesso));
	}

	@Override
	public PaginacaoVO listarPaginado(PaginacaoVO paciente){
		Integer totalRegistros=pacienteDAO.totalRegitrosParaPaginacao(paciente);
		List<Paciente> lista=pacienteDAO.listarPaginado(paciente);
		paginacaoVO.setEntidade(lista);
		paginacaoVO.setTotalRegistros(totalRegistros);
		return paginacaoVO;
	}

	@Override
	public List<Paciente> listar(){
		return pacienteDAO.listar();
	}

	@Override
	public List<Paciente> listarPacienteRelatorio(PaginacaoVO paciente) {
		Integer totalRegistros=pacienteDAO.totalRegitrosParaPaginacao(paciente);
		List<Paciente> lista=pacienteDAO.listarPaginado(paciente);
		paginacaoVO.setEntidade(lista);
		paginacaoVO.setTotalRegistros(totalRegistros);
		return pacienteDAO.listarPacienteRelatorio(paciente);
	}
	
	@Override
	public Paciente obter(Paciente paciente) {
		if (paciente == null) {
			throw new BOException("O PACIENTE não deveria ser nulo");
		}
		
		if (paciente.getSequencial() == null) {
			throw new BOException("O CÓDIGO do PACIENTE não deveria ser nulo");
		}
		
		Paciente pacienteRecuperado = null;
		try {
			pacienteRecuperado = pacienteDAO.obter(paciente);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException("Não foi possível recuperar o paciente");
		}
		
		if (pacienteRecuperado == null) {
			throw new BOException("Nenhum paciente encontrado para o prontuário de número " + paciente.getSequencial());
		}
		
		return pacienteRecuperado;
	}
	
}