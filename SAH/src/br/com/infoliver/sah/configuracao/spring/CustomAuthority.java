package br.com.infoliver.sah.configuracao.spring;

import org.springframework.security.core.GrantedAuthority;

public class CustomAuthority implements GrantedAuthority{
	private static final long serialVersionUID = 1L;

	private String authority;
	
	public CustomAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return this.authority;
	}

}