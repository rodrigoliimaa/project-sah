package br.com.infoliver.sah.negocio.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.LoginException;
import br.com.infoliver.sah.configuracao.spring.CustomAuthentication;
import br.com.infoliver.sah.integracao.dao.IEmpresaDAO;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.facade.IAutenticacaoFacade;
import br.com.infoliver.sah.negocio.vo.LoginVO;

//@Service("autenticacaoFacade")
//@Transactional(propagation = Propagation.REQUIRED)
public class AutenticacaoFacade implements IAutenticacaoFacade{
	/*@Autowired private IEmpresaDAO empresaDAO;
	@Autowired private AuthenticationManager authenticationManager;
	
	@Override
	public LoginVO login(String login, String senha) {
		List permissoes=new ArrayList();
		assertLoginESenha(login, senha);
		CustomAuthentication authenticate = (CustomAuthentication) authenticationManager.authenticate(new CustomAuthentication(login, senha));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		for (int i = 0; i < authenticate.getAuthorities().size(); i++) {
			permissoes.add(authenticate.getAuthorities().get(i).getAuthority());
		}
		LoginVO loginVO=new LoginVO();
		loginVO.setUsuario((Usuario)authenticate.getPrincipal());
		loginVO.setPermissao(permissoes);
		loginVO.setEmpresa(empresaDAO.consultar());
		
		authenticate.isAuthenticated();
	
		return loginVO;
	}

	@Override
	public void sair() {
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
	}

	@Override
	public boolean isAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			throw new AuthenticationCredentialsNotFoundException("Usuário não logado.");
		}
		return true;
	}
	
	private void assertLoginESenha(String login, String senha) {
		if((login == null || login.equals("")) || (senha == null || senha.equals(""))){
			throw new LoginException();
		}
	}
	*/

}