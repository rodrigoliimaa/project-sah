package br.com.infoliver.sah.negocio.bo.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.exception.DBException;
import br.com.infoliver.sah.configuracao.resource.ResourceConstant;
import br.com.infoliver.sah.configuracao.resource.ResourceUtils;
import br.com.infoliver.sah.integracao.dao.IEmpresaDAO;
import br.com.infoliver.sah.integracao.dao.ILogAcessoDAO;
import br.com.infoliver.sah.integracao.dao.IParametroDAO;
import br.com.infoliver.sah.integracao.dao.IPermissaoDAO;
import br.com.infoliver.sah.integracao.dao.IUsuarioDAO;
import br.com.infoliver.sah.negocio.bo.IUsuarioBO;
import br.com.infoliver.sah.negocio.entity.LogAcesso;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.LoginVO;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("usuarioBO")
public class UsuarioBO implements IUsuarioBO {
	@Autowired private IUsuarioDAO usuarioDAO;
	@Autowired private IEmpresaDAO empresaDAO;
	@Autowired private IPermissaoDAO permissaoDAO;
	@Autowired private ILogAcessoDAO logAcessoDAO;
	@Autowired private IParametroDAO parametroDAO;
	@Autowired private PaginacaoVO paginacaoVO;
	@Autowired private RetornoVO retornoVO;
	
	@Override
	public LoginVO acessar(Usuario usuario) throws BOException {
		Usuario usuarioRetorno = usuarioDAO.consultarUsuario(usuario);
		verificaSeExisteUsuario(usuarioRetorno);
		verificarDataBloqueioUsuario(usuarioRetorno);
		verificarAlteracaoSenhaUsuario(usuario,usuarioRetorno);
		verificarPrimeiroAcessoUsuario(usuario,usuarioRetorno);
		gravarLogAcesso(usuarioRetorno);
		// ------------------------------------------------
		LoginVO loginVO=new LoginVO();
		loginVO.setEmpresa(empresaDAO.consultar());
		loginVO.setPermissao(permissaoDAO.listarPermissaoUsuario(usuarioRetorno));
		loginVO.setParametro(parametroDAO.listar());
		// ------------------------------------------------
		usuarioRetorno.setSenha(null);
		usuarioRetorno.setIndicadorPrimeiroAcesso(null);
		// ------------------------------------------------
		loginVO.setUsuario(usuarioRetorno);
		return loginVO;

	}

	private void verificaSeExisteUsuario(Usuario usuario) {
		if (usuario == null)
			throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemAvisoLoginInvalido));
			//Validador.mensagemValidacao(Usuario.class,ResourceConstant.mensagemAvisoEntidadeNaoEncontrada);
	}

	private void verificarDataBloqueioUsuario(Usuario usuario) {
		if (usuario.getDataBloqueio() != null &&
			!usuario.getDataBloqueio().after(Calendar.getInstance().getTime()))
		throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemAvisoUsuarioBloqueado)
				+ "\nMotivo: "+ usuario.getMotivoBloqueio());

	}

	private void verificarPrimeiroAcessoUsuario(Usuario usuario,Usuario usuarioRetorno) {
		if (usuarioRetorno.getIndicadorPrimeiroAcesso().toUpperCase().equals("S")) {
			if (usuarioRetorno.getSenha().equals(usuario.getSenha())&& 
				!usuarioRetorno.getSenha().equals(usuario.getNovaSenha())&&
				(usuario.getNovaSenha() != null && usuario.getNovaSenha().length() > 0))
				alterarSenha(usuario, usuarioRetorno);
			else
				throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemAvisoPrimeiroAcessoUsuario));
		}
	}
	
	private void verificarAlteracaoSenhaUsuario(Usuario usuario,Usuario usuarioRetorno) {
		if (usuarioRetorno.getIndicadorPrimeiroAcesso().toUpperCase().equals("N")) {
			if(usuario.getNovaSenha() != null && usuario.getNovaSenha().length() > 0){
				if (usuarioRetorno.getSenha().equals(usuario.getSenha())&&
					!usuarioRetorno.getSenha().equals(usuario.getNovaSenha()))
					alterarSenha(usuario, usuarioRetorno);
				else
					throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemAvisoSenhaDeveSerDiferenteDaAtual));
			}
		}
	}
	
	private void alterarSenha(Usuario usuario,Usuario usuarioRetorno) {
		try {
			usuarioRetorno.setSenha(usuario.getNovaSenha());
			usuarioRetorno.setIndicadorPrimeiroAcesso("N");
			usuarioDAO.alterarSenha(usuarioRetorno);
		} catch (Exception e) {
			throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemAvisoSenhaNaoAlterada));
		}
	}

	private void gravarLogAcesso(Usuario usuario) {
		LogAcesso logAcesso = new LogAcesso();
		logAcesso.setSequencialUsuario(usuario.getSequencial());
		logAcesso.setNomeUsuario(usuario.getNome());
		logAcessoDAO.inserir(logAcesso);
	}

	@Override
	public RetornoVO inserir(Usuario usuario) throws BOException {
		try {
			List<Usuario> lista=usuarioDAO.inserir(usuario);
			return retornoVO.retornar(lista,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroGravadoComSucesso));
		} catch (Exception e) {
			throw new DBException(e);
		}
	}

	@Override
	public RetornoVO alterar(Usuario usuario) throws BOException {
		try {
			List<Usuario> lista = usuarioDAO.alterar(usuario);
			return retornoVO.retornar(lista,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroAlteradoComSucesso));
		} catch (Exception e) {
			throw new DBException(e);
		}

	}

	@Override
	public RetornoVO excluir(Usuario usuario) throws BOException {
		List<Usuario> lista=usuarioDAO.excluir(usuario);
		return retornoVO.retornar(lista,ResourceUtils.getResourceFromKey(ResourceConstant.mensagemInformativaRegistroExcluidoComSucesso));
	}

	@Override
	public PaginacaoVO listarPaginado(PaginacaoVO usuario){
		Integer totalRegistros=usuarioDAO.totalRegitrosParaPaginacao(usuario);
		List<Usuario> lista=usuarioDAO.listarPaginado(usuario);
		paginacaoVO.setEntidade(lista);
		paginacaoVO.setTotalRegistros(totalRegistros);
		return paginacaoVO;
	}

	@Override
	public List<Usuario> listar(){
		return usuarioDAO.listar();
	}

	@Override
	public List<Usuario> listarUsuarioDoGrupo(Integer sequencialGrupo) {
		return usuarioDAO.listarUsuarioDoGrupo(sequencialGrupo);
	}

	@Override
	public List<Usuario> listarUsuarioDiferenteDoGrupo(Integer sequencialGrupo) {
		return usuarioDAO.listarUsuarioDiferenteDoGrupo(sequencialGrupo);
	}

	@Override
	public Usuario obter(Usuario usuario) {
		return usuarioDAO.obter(usuario);
	}
}