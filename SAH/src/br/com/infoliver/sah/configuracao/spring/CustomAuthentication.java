package br.com.infoliver.sah.configuracao.spring;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import br.com.infoliver.sah.negocio.entity.Usuario;

public class CustomAuthentication implements Authentication{
	private static final long serialVersionUID = 1L;

	private String name;
	private Object credentials;
	private Usuario principal=new Usuario();
	private List<GrantedAuthority> authorities;
	private boolean authenticated;
	
	public CustomAuthentication(){}
	
	public CustomAuthentication(String login, String senha){
		this.name = login;
		this.credentials = senha;
		this.principal.setLogin(login);
		this.principal.setSenha(senha);
	}
	
	public CustomAuthentication(Usuario principal, List<GrantedAuthority> authorities, boolean authenticated){
		this.principal = principal;
		this.authorities = authorities;
		this.authenticated = authenticated;
	}
	
	@Override
	public List<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	@Override
	public boolean isAuthenticated() {
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		this.authenticated = authenticated;
	}

	@Override
	public String getName() {
		return this.name;
	}

}