package br.com.infoliver.sah.configuracao.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import br.com.infoliver.sah.configuracao.exception.LoginException;
import br.com.infoliver.sah.negocio.bo.IUsuarioBO;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.LoginVO;

public class CustomAuthenticationProvider implements AuthenticationProvider{

	private Md5PasswordEncoder passwordEncoder;
	
	@Autowired private IUsuarioBO usuarioBO;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomAuthentication sgdAuthentication = (CustomAuthentication) authentication;
		LoginVO loginVO = verificaSeExisteUsuarioERetornaSeExistir((Usuario) sgdAuthentication.getPrincipal());
		
		//verificaSenha(loginVO.getUsuario().getSenha(), authentication.getCredentials());
		return new CustomAuthentication(loginVO.getUsuario(),carregarPermissoes(loginVO.getPermissao()), true);
	}

	private LoginVO verificaSeExisteUsuarioERetornaSeExistir(Usuario usuario) {
		/*try{
			return usuarioBO.acessar(usuario);
		}catch (Exception e) {
			throw new LoginException();
		}*/
		
		return usuarioBO.acessar(usuario);
	}

	private List<GrantedAuthority> carregarPermissoes(List permissoes) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (int i = 0; i < permissoes.size(); i++) {
			authorities.add(new GrantedAuthorityImpl(permissoes.get(i).toString()));
		} 
		return authorities;
	}
	
	private void verificaSenha(String senha, Object credentials) {
		assertCredentials(credentials);
		String senhaDigitada = passwordEncoder.encodePassword((String)credentials, null);
		if(!senha.equals(senhaDigitada)){
			throw new LoginException();
		}
	}

	private void assertCredentials(Object credentials) {
		if(credentials == null){
			throw new IllegalArgumentException("A senha não pode ser nula");
		}
	}

	public boolean supports(Class authentication) {
		return authentication.isAssignableFrom(CustomAuthentication.class);
	}
	
	public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}